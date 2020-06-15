package com.lunz.fin.config;

import com.lunz.fin.constant.MdcConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


/**
 * @author admin
 * @apiNote web 请求连接器，处理请求token
 * @date 2020-02-14
 */
@Component
@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        MDC.put(MdcConstant.CLIENTID, "");
        MDC.put(MdcConstant.TRACE_ID, "");
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        //开始就要进入调用链
        log.info("[preHandle]向MDC中赋值trace信息");
        String traceId = request.getHeader(MdcConstant.TRACE_ID);
        if (traceId != null) {
            MDC.put(MdcConstant.TRACE_ID, traceId);
        } else {
            log.info("[preHandle]未获取到trace信息，生成一个新的");
            traceId = UUID.randomUUID().toString().replaceAll("-", "");
            MDC.put(MdcConstant.TRACE_ID, traceId);
        }

        String clientId = request.getHeader(MdcConstant.CLIENTID);
        if (clientId != null) {
            MDC.put(MdcConstant.CLIENTID, clientId);
        }

        String requestURI = request.getRequestURI();
        log.info("[web校验]资源请求地址：{}", requestURI);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token, ClientId, AppKey, cloud_access");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
    }


}