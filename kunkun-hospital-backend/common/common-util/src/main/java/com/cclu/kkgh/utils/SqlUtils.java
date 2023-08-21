package com.cclu.kkgh.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ChangCheng Lu
 * @date 2023/8/1 23:57
 */
public class SqlUtils {

    /**
     * 校验排序字段是否合法（防止 SQL 注入）
     *
     * @param sortField
     * @return
     */
    public static boolean validSortField(String sortField) {
        if (StringUtils.isBlank(sortField)) {
            return false;
        }
        return !StringUtils.containsAny(sortField, "=", "(", ")", " ");
    }

}
