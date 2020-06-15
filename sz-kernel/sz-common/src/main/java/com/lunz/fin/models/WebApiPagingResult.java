package com.lunz.fin.models;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author al
 * @date 2019/5/10 11:24
 * @description service接口数据返回统一标准
 */
@Data
public class WebApiPagingResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 执行成功标志:默认成功
    private Boolean success;

    // 执行结果：建议使用 entity | map | list | string
    private List<T> data;

    // 信息
    private String message;

    // 数据总数
    private int count;

    // Code
    private int code;

    WebApiPagingResult(int code, boolean success, String message, int count, List<T> data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
        this.count = count;
    }

    public static <T> WebApiPagingResult<T> error(Exception ex) {
        String exceptionMessageFormat = "Message: %s, StackTrace: %s, Suppressed: %s, Cause: %s, Class: %s %s";

        String msg = String.format(exceptionMessageFormat, ex.getMessage(), ex.getStackTrace(), ex.getSuppressed(),
                ex.getCause(), ex.getClass(), System.getProperty("line.separator"));

        return error(msg);
    }

    public static <T> WebApiPagingResult<T> error(String message) {
        WebApiPagingResult<T> result = new WebApiPagingResult<T>(500, false, message, 0, null);

        return result;
    }
}
