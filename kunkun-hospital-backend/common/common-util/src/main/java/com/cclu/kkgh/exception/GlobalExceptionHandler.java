package com.cclu.kkgh.exception;

import com.cclu.kkgh.result.BaseResponse;
import com.cclu.kkgh.result.BaseResponseCodeEnum;
import com.cclu.kkgh.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ChangCheng Lu
 * @date 2023/6/29 20:07
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        return ResultUtil.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return ResultUtil.fail(e.getMessage(), BaseResponseCodeEnum.SYSTEM_ERROR);
    }

}