package com.lunz.fin.gateway.aspect;

import com.alibaba.fastjson.JSONObject;
import com.lunz.fin.gateway.util.ExceptionUtil;
import com.lunz.fin.models.WebApiResult;
import com.lunz.kernel.exceptions.NotFoundResourceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Liuruixia
 * @Description: security切面异常处理
 * @date 2019/07/26
 */
@Aspect
@Component
public class FilterChainProxyAdvice {
    @Autowired
    private ExceptionUtil executionUtil;

    @Around("execution(public void org.springframework.security.web.FilterChainProxy.doFilter(..))")
    public void handleRequestRejectedException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            proceedingJoinPoint.proceed();
        } catch (RequestRejectedException exception) {
            HttpServletResponse response = (HttpServletResponse) proceedingJoinPoint.getArgs()[1];

            WebApiResult result = executionUtil.getBaseException(new NotFoundResourceException());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().append(JSONObject.toJSONString(result));
            response.setStatus(404);
            response.flushBuffer();
        }
    }
}
