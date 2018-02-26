package org.xxz.myblog.enums;

import org.xxz.myblog.common.exception.ExceptionStatus;

/**
 * 异常号段(10000 - 19999)
 * @author tt
 */
public enum ArticleExceptionEnum implements ExceptionStatus {

    NOT_EDIT_ARTICLE(10000, "不能编辑文章"),
    NOT_DELETE_ARTICLE(10001, "不能删除文章"),
    NOT_FOUND_ARTICLE(10002, "文章不存在"),
    FIXED_LINK_EXISTS(10003, "固定链接已存在")
    ;
    private final int code;
    private final String desc;
    ArticleExceptionEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public final int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
