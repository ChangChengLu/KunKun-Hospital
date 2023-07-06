package com.cclu.kkgh.model.entity.hosp;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 医院设置表
 * @TableName hospital_info
 */
@TableName(value ="hospital_info")
@Data
public class HospitalInfo implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 医院名称
     */
    private String hospName;

    /**
     * 医院编号
     */
    private String hospCode;

    /**
     * api基础路径
     */
    private String apiUrl;

    /**
     * 签名秘钥
     */
    private String signKey;

    /**
     * 联系人
     */
    private String contactName;

    /**
     * 联系人手机
     */
    private String contactPhone;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除(1:已删除，0:未删除)
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}