package com.lunz.fin.gateway.util;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author Liuruixia
 * @Description: 重写错误处理
 * @date 2019/06/28
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {
    private void addStatus(Map<String, Object> errorAttributes,
                           RequestAttributes requestAttributes) {
    }

    private void addErrorDetails(Map<String, Object> errorAttributes,
                                 WebRequest webRequest, boolean includeStackTrace) {
    }

    private void addErrorMessage(Map<String, Object> errorAttributes, Throwable error) {
    }

    private void addStackTrace(Map<String, Object> errorAttributes, Throwable error) {

    }

    private void addPath(Map<String, Object> errorAttributes,
                         RequestAttributes requestAttributes) {
    }
}
