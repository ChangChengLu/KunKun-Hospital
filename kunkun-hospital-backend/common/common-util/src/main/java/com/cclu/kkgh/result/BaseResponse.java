package com.cclu.kkgh.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ChangCheng Lu
 * @date 2023/6/28 16:57
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class BaseResponse<T> implements Serializable {
    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public BaseResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(Integer code, String message) {
        this(code, message, null);
    }

    public BaseResponse(T data) {
        this(null, null, data);
    }

    public BaseResponse(BaseResponseCodeEnums baseResponseCodeEnums) {
        this(baseResponseCodeEnums.getCode(), baseResponseCodeEnums.getMessage());
    }

    public BaseResponse(T data, BaseResponseCodeEnums baseResponseCodeEnums) {
        this(baseResponseCodeEnums.getCode(), baseResponseCodeEnums.getMessage(), data);
    }
}
