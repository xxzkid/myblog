package org.xxz.myblog.enums;

/**
 * @author tt
 */
public enum StatusEnum {

    OK(1, "有效"),
    NOT_OK(0, "无效"),
    ;

    private final int key;
    private final String value;
    StatusEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
