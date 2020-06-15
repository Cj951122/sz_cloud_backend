package com.lunz.fin.gateway.config;

import com.alibaba.fastjson.JSONObject;
import com.lunz.fin.gateway.util.ExceptionUtil;
import com.lunz.fin.models.WebApiResult;
import com.lunz.kernel.exceptions.InvalidTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoderJwkSupport;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Liuruixia
 * @Description:
 * @date 2019/06/24
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    @Autowired
    private ExceptionUtil exceptionUtil;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(authorizationResponseFilter(), ChannelProcessingFilter.class)
                .authorizeRequests()
                .antMatchers("/manage/health").permitAll()
                .anyRequest().authenticated()
                .and().logout().permitAll()
                .and().csrf().disable()
                .cors().and().oauth2ResourceServer().jwt();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/**/v2/api-docs");
        web.ignoring().antMatchers("/swagger-ui.html**");
        web.ignoring().antMatchers("/webjars/**");
        web.ignoring().antMatchers("/swagger-resources/**");
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return new NimbusJwtDecoderJwkSupport(jwkSetUri);
    }

    /**
     * 处理校验filter
     *
     * @return
     */
    private Filter authorizationResponseFilter() {
        return (request, response, chain) -> {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            chain.doFilter(request, response);

            HttpStatus httpStatus = HttpStatus.valueOf(httpServletResponse.getStatus());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

            if (!response.isCommitted() && httpStatus == HttpStatus.UNAUTHORIZED) {

                WebApiResult result = exceptionUtil.getBaseException(new InvalidTokenException());
                response.getWriter().append(JSONObject.toJSONString(result));
            }
        };
    }
}
