package com.cclu.kkgh.model.dto.hosp;

import com.cclu.kkgh.common.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ChangCheng Lu
 * @date 2023/6/28 17:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HospitalInfoQueryRequest extends PageRequest {

    @ApiModelProperty(value = "医院名称")
    private String hospName;

    @ApiModelProperty(value = "医院编号")
    private String hospCode;

}
