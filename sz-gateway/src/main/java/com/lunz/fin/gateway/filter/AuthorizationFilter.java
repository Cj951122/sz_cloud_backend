package com.lunz.fin.gateway.filter;

import com.lunz.fin.constant.Constants;
import com.lunz.fin.constant.MdcConstant;
import com.lunz.fin.gateway.constant.CommonConstants;
import com.lunz.fin.gateway.util.ExceptionUtil;
import com.lunz.kernel.exceptions.InvalidTokenException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

/**
 * @author Liuruixia
 * @Description: 鉴权Filter
 * @date 2019/06/24
 */
@Component
@Slf4j
public class AuthorizationFilter extends ZuulFilter {
    @Autowired
    JwtDecoder jwtDecoder;

    @Autowired
    private ExceptionUtil exceptionUtil;
    @Override
    public boolean shouldFilter() {

        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();
            if (request.getRequestURI().contains(CommonConstants.SWAGGER_PREFIX) || request.getRequestURI().contains(CommonConstants.WEBJAR_KEY)
                    || request.getRequestURI().contains(CommonConstants.CSRF_KEY)
                    || request.getRequestURI().contains(CommonConstants.SWAGGER_DOC_PATH)) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        try {
            RequestContext requestContext = RequestContext.getCurrentContext();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String authorizationHeaderValue = requestContext.getRequest().getHeader("Authorization");

            MDC.put(MdcConstant.CLIENTID, "");
            MDC.put(MdcConstant.TRACE_ID,"");

            //设置调用链
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            requestContext.addZuulRequestHeader(MdcConstant.TRACE_ID, uuid);
            MDC.put(MdcConstant.TRACE_ID,uuid);

            if (StringUtils.isEmpty(authorizationHeaderValue) || !authorizationHeaderValue.startsWith(CommonConstants.BEARER_TOKEN_PREFIX)) {
                requestContext.setResponseStatusCode(401);
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseBody(exceptionUtil.getBaseException(new InvalidTokenException()).toString());
                //不再进行转发 直接返回
                return null;
            }

            Map<String, Object> claims = ((Jwt) authentication.getPrincipal()).getClaims();
            Object client = claims.get("sub");
            if (client != null) {
                MDC.put(MdcConstant.CLIENTID, client.toString());
                requestContext.addZuulRequestHeader(MdcConstant.CLIENTID, client.toString());
            }

            String token = JSONObject.toJSONString(claims);

            requestContext.addZuulRequestHeader(Constants.AUTH_USER_DETAIL, URLEncoder.encode(token, "UTF-8"));
            log.info("[jwt]用户信息：{}", token);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error("Claims转换不支持的Encoding失败");
            throw new InvalidTokenException("Claims转换不支持的Encoding失败");
        } catch (Exception e) {
            log.error("Claims转换失败");
            e.printStackTrace();
            throw new InvalidTokenException("Claims转换失败");
        }
        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

}
