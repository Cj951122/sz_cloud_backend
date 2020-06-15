package com.lunz.fin.utils;

import com.alibaba.fastjson.JSONObject;
import com.lunz.fin.constant.Constants;
import com.lunz.fin.models.ApplicationRole;
import com.lunz.fin.models.UserDetails;
import com.lunz.kernel.exceptions.InvalidTokenException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liuruixia
 * @Description: 登录用户通用类
 * @date 2019/06/20
 */
public class UserDetailsUtil {

    /**
     * 获取用户详情from token
     *
     * @param claimsBody
     * @return
     */
    public static UserDetails getUserInfoFromClaimBody(String claimsBody) {
        try {
            List<ApplicationRole> roles = new ArrayList<>();
            JSONObject jsonObj = JSONObject.parseObject(claimsBody);
            Object roleObject = jsonObj.get(Constants.CLAIM_ROLE_KEY);

            if (roleObject != null) {
                List<String> roleList = new ArrayList<>();
                String roleString = roleObject.toString();

                if (StringUtils.indexOf(roleString, Constants.LEFT_SQUARE_BRACKET) < 0) {
                    roleList.add(roleString);
                } else {
                    roleList = JSONObject.parseArray(roleString, String.class);
                }
                
                roleList.forEach(role -> {
                    String[] arr = role.split(":");
                    roles.add(new ApplicationRole(arr[0], arr[1]));
                });
            }

            UserDetails userDetails = JSONObject.parseObject(claimsBody, UserDetails.class);
            userDetails.setRoles(roles);
            return userDetails;

        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidTokenException();
        }
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public static UserDetails getCurrentUser() {

        String claimsBody;
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();

            String token = request.getHeader(Constants.AUTH_USER_DETAIL);
            claimsBody = URLDecoder.decode(token, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidTokenException();
        }

        return UserDetailsUtil.getUserInfoFromClaimBody(claimsBody);
    }

    /**
     * 获取当前用户ID
     *
     * @return
     */
    public static String getCurrentUserId() {
        UserDetails userDetails = getCurrentUser();
        if (userDetails == null || StringUtils.isBlank(userDetails.getId())) {
            throw new InvalidTokenException();
        }
        return userDetails.getId();
    }

}
