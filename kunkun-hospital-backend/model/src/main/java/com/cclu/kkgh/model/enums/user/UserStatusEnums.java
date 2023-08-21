package com.cclu.kkgh.model.enums.user;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChangCheng Lu
 * @date 2023/8/1 23:24
 */
public enum UserStatusEnums {

    /**
     * userRole
     */
    PERMIT("正常", 1),
    BAN("被封号", 0);

    private final String text;

    private final int value;

    UserStatusEnums(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public static UserStatusEnums getEnumByValue(int value) {
        for (UserStatusEnums userStatusEnums : UserStatusEnums.values()) {
            if (userStatusEnums.value == value) {
                return userStatusEnums;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

}
