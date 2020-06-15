package com.lunz.fin.models;

import lombok.Data;

import java.io.Serializable;

/**
 * @author al
 * @date 2019/5/10 11:24
 * @description service接口数据返回统一标准
 */
@Data
public class WebApiResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 执行成功标志:默认成功
    protected Boolean success = true;

    // 执行结果：建议使用 entity | map | list | string
    protected T data;

    // 信息
    protected String message;

    // 数据总数
    protected int count;

    // Code
    protected String code;

    public WebApiResult() {

    }

    WebApiResult(String code, boolean success, String message, int count, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
        this.count = count;
    }

    /**
     * 分页单个数据
     */
    public static <T> WebApiResult<T> ok(T data) {
        try {
            WebApiResult<T> result = new WebApiResult<T>("200", true, "success", 1, data);

            return result;
        } catch (Exception ex) {
            return error(ex);
        }
    }

    public static <T> WebApiResult<T> error(Exception ex) {
        String exceptionMessageFormat = "Message: %s, StackTrace: %s, Suppressed: %s, Cause: %s, Class: %s %s";

        String msg = String.format(exceptionMessageFormat, ex.getMessage(), ex.getStackTrace(), ex.getSuppressed(),
                ex.getCause(), ex.getClass(), System.getProperty("line.separator"));

        return error(msg);
    }

    public static <T> WebApiResult<T> error(String message) {
        WebApiResult<T> result = new WebApiResult<T>("500", false, message, 0, null);
        result.count = 0;

        return result;
    }

    public static <T> WebApiResult<T> error(String code, String message) {
        WebApiResult<T> result = new WebApiResult<T>(code, false, message, 0, null);
        return result;
    }
}
