package com.lunz.fin.exception;

/**
 * <p>业务异常</p>
 * <p>业务处理时，出现异常，可以抛出该异常</p>
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private String code;


    public BusinessException(IResponseEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.msg = exceptionEnum.getMessage();
        this.code = exceptionEnum.getCode();
    }


    public BusinessException(IResponseEnum exceptionEnum, Throwable e) {
        super(exceptionEnum.getMessage(), e);
        this.msg = exceptionEnum.getMessage();
        this.code = exceptionEnum.getCode();
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
