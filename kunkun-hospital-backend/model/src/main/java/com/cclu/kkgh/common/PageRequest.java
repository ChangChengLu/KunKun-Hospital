package com.cclu.kkgh.common;

import com.cclu.kkgh.constant.CommonConstant;
import lombok.Data;

/**
 * @author ChangCheng Lu
 * @date 2023/6/28 17:55
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private long current = 1;

    /**
     * 页面大小
     */
    private long pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;

}
