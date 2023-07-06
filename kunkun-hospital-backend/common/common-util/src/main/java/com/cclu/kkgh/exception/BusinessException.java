package com.cclu.kkgh.exception;

import com.cclu.kkgh.result.BaseResponseCodeEnum;

/**
 * @author ChangCheng Lu
 * @date 2023/6/29 20:07
 */
public class BusinessException extends RuntimeException {

    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(BaseResponseCodeEnum baseResponseCodeEnum) {
        super(baseResponseCodeEnum.getMessage());
        this.code = baseResponseCodeEnum.getCode();
    }

    public BusinessException(BaseResponseCodeEnum baseResponseCodeEnum, String message) {
        super(message);
        this.code = baseResponseCodeEnum.getCode();
    }

    public int getCode() {
        return code;
    }

}
