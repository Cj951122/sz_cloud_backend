package com.lunz.fin.gateway.config;

import com.lunz.fin.gateway.util.GlobalErrorAttributes;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Liuruixia
 * @Description: 全局错误处理设置
 * @date 2019/06/28
 */
@Configuration

public class ErrorConfiguration {
    @Bean
    @ConditionalOnMissingBean(value = ErrorAttributes.class,
            search = SearchStrategy.CURRENT)
    public GlobalErrorAttributes errorAttributes() {
        return new GlobalErrorAttributes();
    }

    @Bean
    @ConditionalOnMissingBean(value = ErrorProperties.class,
            search = SearchStrategy.CURRENT)
    public ErrorProperties errorProperties() {
        return new ErrorProperties();
    }
}
