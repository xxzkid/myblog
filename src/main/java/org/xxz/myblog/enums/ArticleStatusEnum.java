package org.xxz.myblog.enums;

/**
 * @author tt
 */
public enum ArticleStatusEnum {

    ALL(-1, "所有"),
    SHOW(0, "显示"),
    HIDDEN(1, "隐藏"),
    ;

    private final int key;
    private final String value;

    ArticleStatusEnum(int key, String value) {
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
