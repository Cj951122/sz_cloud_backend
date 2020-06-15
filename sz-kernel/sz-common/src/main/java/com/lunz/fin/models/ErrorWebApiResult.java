package com.lunz.fin.models;

import com.lunz.fin.constant.Constants;
import lombok.Data;

/**
 * @author zy
 * @date 2020/6/6
 * @description service接口数据返回统一标准
 */
@Data
public class ErrorWebApiResult extends WebApiResult {

    // 信息
    private String[] errorMessages;
    private String errorCode;

    ErrorWebApiResult(String code, String message, String[] errorMessages) {
        this.code           = code;
        this.success        = false;
        this.message        = message;
        this.errorMessages  = errorMessages;
        this.errorCode      = Constants.ERRORCODEPREFIX +code;
    }


    public static  ErrorWebApiResult error(String code, String message, String errorMessage) {
        return new ErrorWebApiResult(code, message, new String[]{errorMessage});
    }

    public static  ErrorWebApiResult error(int code, String message, String errorMessage) {
        return error( code + "", message, errorMessage);
    }
}
