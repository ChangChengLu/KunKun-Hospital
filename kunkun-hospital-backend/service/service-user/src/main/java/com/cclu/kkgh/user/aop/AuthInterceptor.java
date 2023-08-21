package com.cclu.kkgh.user.aop;

import com.cclu.kkgh.annoation.AuthCheck;
import com.cclu.kkgh.exception.BusinessException;
import com.cclu.kkgh.model.entity.user.User;
import com.cclu.kkgh.model.enums.user.UserRoleEnums;
import com.cclu.kkgh.model.enums.user.UserStatusEnums;
import com.cclu.kkgh.result.BaseResponseCodeEnums;
import com.cclu.kkgh.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ChangCheng Lu
 * @date 2023/8/1 16:50
 *
 * 权限校验 AOP
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        User loginUser = userService.getLoginUser(request);
        if (StringUtils.isNotBlank(mustRole)) {
            UserRoleEnums mustUserRoleEnums = UserRoleEnums.getEnumByValue(mustRole);
            if (mustUserRoleEnums == null) {
                throw new BusinessException(BaseResponseCodeEnums.NO_AUTH_ERROR);
            }
            Integer userStatus = loginUser.getUserStatus();
            UserStatusEnums userStatusEnums = UserStatusEnums.getEnumByValue(userStatus);
            if (userStatusEnums == null) {
                throw new BusinessException(BaseResponseCodeEnums.NO_AUTH_ERROR);
            }
            if (UserStatusEnums.BAN.equals(userStatusEnums)) {
                throw new BusinessException(BaseResponseCodeEnums.NO_AUTH_ERROR);
            }
            String userRole = loginUser.getUserRole();
            if (UserRoleEnums.ADMIN.equals(mustUserRoleEnums)) {
                if (!mustRole.equals(userRole)) {
                    throw new BusinessException(BaseResponseCodeEnums.NO_AUTH_ERROR);
                }
            }
        }
        return joinPoint.proceed();
    }
}
