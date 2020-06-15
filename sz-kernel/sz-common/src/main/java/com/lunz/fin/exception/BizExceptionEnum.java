package com.lunz.fin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务异常枚举类
 */
@Getter
@AllArgsConstructor
public enum BizExceptionEnum implements IResponseEnum {

    NOT_KNOWN("100.000", "服务器发生未知错误，请重试"),
    IO_ERROR("100.001", "IO操作异常"),
    STACK_OVERFLOW("100.002", "栈溢出"),
    INVALID_CAST("100.003", "指定的转换无效"),
    FORMAT_ERROR("100.004", "输入的字符串格式不正确"),
    SERIALIZATION_ERROR("100.005", "序列化错误"),
    KEY_NOT_FOUND("100.006", "给定关键字不在字典中"),
    NULL_POINTER("100.007", "空指针异常"),
    ARGUMENT_NULL("100.008", "参数值不能为空"),
    NOT_IMPLEMENT("100.009", "存在未实现的方法"),
    INVALID_TOKEN("101.001", "token无效"),
    INVALID_SCOPE("101.002", "scope无效"),
    PERMISSION_DENIED("102.001", "权限受限"),
    NOT_FOUND_DATA("103.001", "数据不存在"),
    NOT_FOUND_Resource("103.002", "资源不存在"),
    NULL_PARAMETER("104.001", "参数不能为空"),
    LENGTH_PARAMETER("104.002", "参数长度错误"),
    INVALID_PARAMETER("104.003", "参数无效"),
    INVALID_RANGE("104.004", "参数界限值错误"),
    HTTP_REQUEST_ERROR("105.001", "找不到主机"),
    HTTP_RESPONSE_ERROR("105.002", "Http调用异常");

    /**
     * 返回码
     */
    private String code;
    /**
     * 返回消息
     */
    private String message;
}
