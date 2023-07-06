package com.cclu.kkgh.model.enums;

import com.cclu.kkgh.exception.BusinessException;
import com.cclu.kkgh.result.BaseResponseCodeEnum;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChangCheng Lu
 * @date 2023/6/29 20:52
 */
@Getter
public enum HospitalInfoStatusEnums {

    /**
     * LOCK(0, "禁止修改"),
     * UNLOCK(1, "允许修改");
     */
    LOCK(0, "禁止修改"),
    UNLOCK(1, "允许修改");

    private final int code;

    private final String message;


    HospitalInfoStatusEnums(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public static HospitalInfoStatusEnums getEnumByCode(Integer code) {
        if (code == null) {
            throw new BusinessException(BaseResponseCodeEnum.PARAM_ERROR);
        }

        HospitalInfoStatusEnums[] interfaceInfoStatusEnums = HospitalInfoStatusEnums.values();
        for (HospitalInfoStatusEnums hospitalInfoStatusEnums : interfaceInfoStatusEnums) {
            if (hospitalInfoStatusEnums.getCode() == code) {
                return hospitalInfoStatusEnums;
            }
        }

        return null;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.code).collect(Collectors.toList());
    }

}
