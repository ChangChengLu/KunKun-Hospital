package com.cclu.kkgh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cclu.kkgh.common.IdRequest;
import com.cclu.kkgh.constant.CommonConstant;
import com.cclu.kkgh.exception.BusinessException;
import com.cclu.kkgh.hosp.service.HospitalInfoService;
import com.cclu.kkgh.model.dto.hosp.HospitalInfoQueryRequest;
import com.cclu.kkgh.model.entity.hosp.HospitalInfo;
import com.cclu.kkgh.model.enums.HospitalInfoStatusEnums;
import com.cclu.kkgh.result.BaseResponse;
import com.cclu.kkgh.result.BaseResponseCodeEnum;
import com.cclu.kkgh.result.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ChangCheng Lu
 * @date 2023/6/28 16:40
 */
@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalInfo")
public class HospitalInfoController {

    @Resource
    private HospitalInfoService hospitalInfoService;


    @GetMapping("/list/all")
    public BaseResponse<List<HospitalInfo>> listAllHospitalInfo() {
        List<HospitalInfo> list = hospitalInfoService.list();
        return ResultUtil.ok(list);
    }

    @GetMapping("/delete")
    public BaseResponse<Boolean> removeHospitalInfo(@RequestBody IdRequest idRequest) {
        boolean flag = hospitalInfoService.removeById(idRequest.getId());
        return flag ? ResultUtil.ok() : ResultUtil.fail();
    }

    @PostMapping("/list/page")
    public BaseResponse<Page<HospitalInfo>> listPageHospInfo(@RequestBody HospitalInfoQueryRequest hospitalInfoQueryRequest) {
        if (hospitalInfoQueryRequest == null) {
            throw new BusinessException(BaseResponseCodeEnum.PARAM_ERROR);
        }

        HospitalInfo hospitalInfo = new HospitalInfo();
        BeanUtils.copyProperties(hospitalInfoQueryRequest, hospitalInfo);

        long current = hospitalInfoQueryRequest.getCurrent();
        long pageSize = hospitalInfoQueryRequest.getPageSize();
        String sortField = hospitalInfoQueryRequest.getSortField();
        String sortOrder = hospitalInfoQueryRequest.getSortOrder();

        QueryWrapper<HospitalInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);

        Page<HospitalInfo> page = hospitalInfoService.page(new Page<>(current, pageSize), queryWrapper);

        return ResultUtil.ok(page);
    }

    @PostMapping("/add")
    public BaseResponse<Boolean> saveHospital(@RequestBody HospitalInfo hospitalInfo) {
        // todo 设置签名算法

        boolean save = hospitalInfoService.save(hospitalInfo);
        return save ? ResultUtil.ok() : ResultUtil.fail();
    }

    @PostMapping("/get")
    public BaseResponse<HospitalInfo> getHospitalInfoById(@RequestBody IdRequest idRequest) {
        if (idRequest == null) {
            throw new BusinessException(BaseResponseCodeEnum.PARAM_ERROR);
        }

        HospitalInfo hospitalInfo = hospitalInfoService.getById(idRequest.getId());

        return ResultUtil.ok(hospitalInfo);
    }

    @PostMapping("/update")
    public BaseResponse<HospitalInfo> updateHospitalInfo(@RequestBody HospitalInfo hospitalInfo) {
        if (hospitalInfo == null) {
            throw new BusinessException(BaseResponseCodeEnum.PARAM_ERROR);
        }

        // todo 参数校验

        boolean flag = hospitalInfoService.updateById(hospitalInfo);

        return flag ? ResultUtil.ok() : ResultUtil.fail();
    }

    @PostMapping("/delete/batch")
    public BaseResponse<Boolean> removeBatchHospital(List<Long> idList) {
        boolean flag = hospitalInfoService.removeByIds(idList);

        return flag ? ResultUtil.ok() : ResultUtil.fail();
    }

    @PostMapping("/sendKey")
    public BaseResponse senSignKey(@RequestBody IdRequest idRequest) {
        if (idRequest == null) {
            throw new BusinessException(BaseResponseCodeEnum.PARAM_ERROR);
        }
        HospitalInfo hospitalInfo = hospitalInfoService.getById(idRequest.getId());
        if (hospitalInfo == null) {
            throw new BusinessException(BaseResponseCodeEnum.PARAM_ERROR);
        }
        String signKey = hospitalInfo.getSignKey();
        String hospCode = hospitalInfo.getHospCode();
        //todo 发送短信
        return ResultUtil.ok();
    }

    @PostMapping("/lock")
    public BaseResponse lockHospitalInfo(@RequestBody IdRequest idRequest) {
        if (idRequest == null) {
            throw new BusinessException(BaseResponseCodeEnum.PARAM_ERROR);
        }
        long id = idRequest.getId();
        UpdateWrapper<HospitalInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", HospitalInfoStatusEnums.LOCK.getCode());

        boolean update = hospitalInfoService.update(updateWrapper);

        return update ? ResultUtil.ok() : ResultUtil.fail();
    }

    @PostMapping("/unlock")
    public BaseResponse unlockHospitalInfo(@RequestBody IdRequest idRequest) {
        if (idRequest == null) {
            throw new BusinessException(BaseResponseCodeEnum.PARAM_ERROR);
        }
        long id = idRequest.getId();
        UpdateWrapper<HospitalInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", HospitalInfoStatusEnums.UNLOCK.getCode());

        boolean update = hospitalInfoService.update(updateWrapper);

        return update ? ResultUtil.ok() : ResultUtil.fail();
    }
}
