package com.cclu.kkgh.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.kkgh.constant.CommonConstant;
import com.cclu.kkgh.exception.BusinessException;
import com.cclu.kkgh.model.dto.user.UserQueryRequest;
import com.cclu.kkgh.model.vo.user.LoginUserVO;
import com.cclu.kkgh.model.vo.user.UserVO;
import com.cclu.kkgh.model.entity.user.User;
import com.cclu.kkgh.model.enums.user.UserRoleEnums;
import com.cclu.kkgh.result.BaseResponseCodeEnums;
import com.cclu.kkgh.user.mapper.UserMapper;
import com.cclu.kkgh.user.service.UserService;
import com.cclu.kkgh.utils.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.cclu.kkgh.constant.UserConstant.*;

/**
* @author 21237
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2023-08-01 17:22:29
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(BaseResponseCodeEnums.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < USER_ACCOUNT_MIN_LENGTH) {
            throw new BusinessException(BaseResponseCodeEnums.PARAMS_ERROR, "用户账号过短");
        }
        if (userPassword.length() < USER_PASSWORD_MIN_LENGTH || checkPassword.length() < USER_PASSWORD_MIN_LENGTH) {
            throw new BusinessException(BaseResponseCodeEnums.PARAMS_ERROR, "用户密码过短");
        }
        if (!checkPassword.equals(userPassword)) {
            throw new BusinessException(BaseResponseCodeEnums.PARAMS_ERROR, "两次输入的密码不一致");
        }
        synchronized (userAccount.intern()) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userAccount", userAccount);
            Integer count = baseMapper.selectCount(queryWrapper);
            if (count > 0) {
                throw new BusinessException(BaseResponseCodeEnums.PARAMS_ERROR, "账号重复");
            }
            String encryptPassword = DigestUtils.md5DigestAsHex((USER_SALT + userPassword).getBytes());
            User user = new User();
            user.setUserAccount(userAccount);
            user.setUserPassword(encryptPassword);
            boolean resultSave = save(user);
            if (!resultSave) {
                throw new BusinessException(BaseResponseCodeEnums.SYSTEM_ERROR, "注册失败，数据库错误");
            }
            return user.getId();
        }
    }

    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(BaseResponseCodeEnums.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < USER_ACCOUNT_MIN_LENGTH) {
            throw new BusinessException(BaseResponseCodeEnums.PARAMS_ERROR, "用户账号过短");
        }
        if (userPassword.length() < USER_PASSWORD_MIN_LENGTH) {
            throw new BusinessException(BaseResponseCodeEnums.PARAMS_ERROR, "用户密码过短");
        }
        String encryptPassword = DigestUtils.md5DigestAsHex((USER_SALT + userPassword).getBytes());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(BaseResponseCodeEnums.PARAMS_ERROR, "用户不存在或密码错误");
        }
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return getLoginUserVO(user);
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute(USER_LOGIN_STATE) == null) {
            throw new BusinessException(BaseResponseCodeEnums.OPERATION_ERROR, "未登录");
        }
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(BaseResponseCodeEnums.NOT_LOGIN_ERROR);
        }
        Long userId = currentUser.getId();
        currentUser = userMapper.selectById(userId);
        if (currentUser == null) {
            throw new BusinessException(BaseResponseCodeEnums.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    @Override
    public User getLoginUserPermitNull(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if (currentUser == null || currentUser.getId() == null) {
            return null;
        }
        Long userId = currentUser.getId();
        currentUser = userMapper.selectById(userId);
        return currentUser;
    }

    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if (user == null) {
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    @Override
    public UserVO getUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public List<UserVO> getUserVO(List<User> userList) {
        if (userList == null) {
            return new ArrayList<>();
        }
        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        return isAdmin((User) request.getSession().getAttribute(USER_LOGIN_STATE));
    }

    @Override
    public boolean isAdmin(User user) {
        return user != null && UserRoleEnums.ADMIN.getValue().equals(user.getUserRole());
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(BaseResponseCodeEnums.PARAMS_ERROR, "请求参数为空");
        }
        Long id = userQueryRequest.getId();
        String unionId = userQueryRequest.getUnionId();
        String mpOpenId = userQueryRequest.getMpOpenId();
        String userName = userQueryRequest.getUserName();
        String userProfile = userQueryRequest.getUserProfile();
        String userRole = userQueryRequest.getUserRole();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(StringUtils.isNotBlank(unionId), "unionId", unionId);
        queryWrapper.eq(StringUtils.isNotBlank(mpOpenId), "mpOpenId", mpOpenId);
        queryWrapper.eq(StringUtils.isNotBlank(userRole), "userRole", userRole);
        queryWrapper.like(StringUtils.isNotBlank(userProfile), "userProfile", userProfile);
        queryWrapper.like(StringUtils.isNotBlank(userName), "userName", userName);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




