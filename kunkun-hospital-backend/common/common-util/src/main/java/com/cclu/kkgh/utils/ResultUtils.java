package com.cclu.kkgh.utils;

import com.cclu.kkgh.result.BaseResponse;
import com.cclu.kkgh.result.BaseResponseCodeEnums;

/**
 * @author ChangCheng Lu
 * @date 2023/6/28 17:06
 */
public class ResultUtils {

    public static<T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(data, BaseResponseCodeEnums.SUCCESS);
    }

    public static<T> BaseResponse<T> success() {
        return success(null);
    }

    public static<T> BaseResponse<T> fail(T data, BaseResponseCodeEnums baseResponseCodeEnums) {
        return new BaseResponse<>(data, baseResponseCodeEnums);
    }

    public static<T> BaseResponse<T> fail(BaseResponseCodeEnums baseResponseCodeEnums) {
        return new BaseResponse<>(baseResponseCodeEnums);
    }

    public static<T> BaseResponse<T> fail() {
        return new BaseResponse<T>(BaseResponseCodeEnums.SYSTEM_ERROR);
    }

    public static<T> BaseResponse<T> fail(Integer code, String message) {
        return new BaseResponse<>(code, message);
    }
}
