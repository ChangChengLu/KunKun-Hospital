package com.cclu.kkgh.result;

import com.fasterxml.jackson.databind.ser.Serializers;

/**
 * @author ChangCheng Lu
 * @date 2023/6/28 17:06
 */
public class ResultUtil {

    public static<T> BaseResponse<T> ok(T data) {
        return new BaseResponse<>(data, BaseResponseCodeEnum.SUCCESS);
    }

    public static<T> BaseResponse<T> ok() {
        return ok(null);
    }

    public static<T> BaseResponse<T> fail(T data, BaseResponseCodeEnum baseResponseCodeEnum) {
        return new BaseResponse<>(data, baseResponseCodeEnum);
    }

    public static<T> BaseResponse<T> fail(BaseResponseCodeEnum baseResponseCodeEnum) {
        return new BaseResponse<>(baseResponseCodeEnum);
    }

    public static<T> BaseResponse<T> fail() {
        return new BaseResponse<T>(BaseResponseCodeEnum.SYSTEM_ERROR);
    }

    public static<T> BaseResponse<T> fail(Integer code, String message) {
        return new BaseResponse<>(code, message);
    }
}
