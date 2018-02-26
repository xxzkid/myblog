package org.xxz.myblog.common.enums;

import org.xxz.myblog.common.constant.ResultConst;
import org.xxz.myblog.common.exception.ExceptionStatus;

/**
 * @author tt
 */
public enum ErrorEnum implements ExceptionStatus {

    PARAM_ERROR(ResultConst.FAIL, "参数错误"),
    NOT_LOGIN(ResultConst.NOT_LOGIN, "无权限"),
    ;
    private final int code;
    private final String desc;
    ErrorEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
