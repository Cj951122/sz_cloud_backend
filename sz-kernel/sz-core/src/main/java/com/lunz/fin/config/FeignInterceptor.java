package com.lunz.fin.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author al
 * @date 2020/2/11 14:35
 * @description feign 在服务间传递参数信息
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            Map<String,String> headers = this.getHeaders();
            for(String headerName : headers.keySet()){
                requestTemplate.header(headerName, headers.get(headerName));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Map<String, String> getHeaders(){
        //获取请求报文头部元数据: 有问题，获取不到requestAttributes
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取请求对象
        HttpServletRequest request = requestAttributes.getRequest();

        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
