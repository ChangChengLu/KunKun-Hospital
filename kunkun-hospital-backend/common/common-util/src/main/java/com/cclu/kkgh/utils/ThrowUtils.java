package com.cclu.kkgh.utils;

import com.cclu.kkgh.exception.BusinessException;
import com.cclu.kkgh.result.BaseResponseCodeEnums;

/**
 * @author ChangCheng Lu
 * @date 2023/8/2 10:53
 */
public class ThrowUtils {

    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    public static void throwIf(boolean condition, BaseResponseCodeEnums baseResponseCodeEnums) {
        throwIf(condition, new BusinessException(baseResponseCodeEnums));
    }

    public static void throwIf(boolean condition, BaseResponseCodeEnums baseResponseCodeEnums, String message) {
        throwIf(condition, new BusinessException(baseResponseCodeEnums, message));
    }

}
