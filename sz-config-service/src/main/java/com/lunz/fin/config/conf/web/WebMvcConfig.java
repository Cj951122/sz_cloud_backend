package com.lunz.fin.config.conf.web;

import com.lunz.fin.config.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


/**
 * MVC配置
 * @author admin
 * @apiNote  web 接口请求配置
 * @date 2020-02-14 22:30
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**"
                        , "/v2/**", "/swagger-ui.html/**", "/swagger-ui.html#!/**","/"
                        ,"/manage/health", "/actuator/health");
    }
     
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    }

    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	registry.addMapping("/**")
    	.allowedOrigins("*")
    	.allowedHeaders("*")
        .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
        .allowCredentials(false).maxAge(3600);
    }
}