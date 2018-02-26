package org.xxz.myblog.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.xxz.myblog.common.constant.ResultConst;

/**
 * @author tt
 * @param <T> 数据
 */
@ToString
@Setter
@Getter
public class Result<T> {

    /**
     * 状态
     */
    private int code;
    /**
     * 描述
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultConst.OK);
        result.setMsg(msg == null ? ResultConst.OK_MSG : msg);
        result.setData(data);
        return result;
    }

    public static Result<?> success(String msg) {
        Result<?> result = new Result<>();
        result.setCode(ResultConst.OK);
        result.setMsg(msg == null ? ResultConst.OK_MSG : msg);
        return result;
    }

    public static <T> Result<T> fail(String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultConst.FAIL);
        result.setMsg(msg == null ? ResultConst.FAIL_MSG : msg);
        result.setData(data);
        return result;
    }

    public static Result<?> fail(String msg) {
        Result<?> result = new Result<>();
        result.setCode(ResultConst.FAIL);
        result.setMsg(msg == null ? ResultConst.FAIL_MSG : msg);
        return result;
    }

    public static Result<Throwable> error(Throwable e) {
        Result<Throwable> result = new Result<>();
        result.setCode(ResultConst.FAIL);
        result.setMsg("服务器繁忙，请稍候再试");
        result.setData(e);
        return result;
    }


}
