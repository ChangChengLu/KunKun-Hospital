package com.cclu.kkgh.exception;

import com.cclu.kkgh.result.BaseResponseCodeEnums;

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

    public BusinessException(BaseResponseCodeEnums baseResponseCodeEnums) {
        super(baseResponseCodeEnums.getMessage());
        this.code = baseResponseCodeEnums.getCode();
    }

    public BusinessException(BaseResponseCodeEnums baseResponseCodeEnums, String message) {
        super(message);
        this.code = baseResponseCodeEnums.getCode();
    }

    public int getCode() {
        return code;
    }

}
