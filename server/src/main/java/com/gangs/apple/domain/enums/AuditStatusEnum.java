package com.gangs.apple.domain.enums;

import java.util.HashMap;
import java.util.Map;

public enum AuditStatusEnum {

    Blocked(1, "未审核"),
    Passed(2, "审核通过");

    int code;
    String name;

    AuditStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }


    private static final Map<Integer, AuditStatusEnum> keyMap = new HashMap<>();

    static {
        for (AuditStatusEnum item : AuditStatusEnum.values()) {
            keyMap.put(item.getCode(), item);
        }
    }

    public static AuditStatusEnum fromCode(Integer code) {
        return keyMap.get(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
