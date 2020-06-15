package com.lunz.fin.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.lunz.fin.constant.Constants;
import com.lunz.kernel.exceptions.core.BaseException;
import com.lunz.kernel.exceptions.util.GlobalExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Liuruixia
 * @Description:错误异常公共类
 * @date 2019/07/17
 */
@Component
public class ExceptionResultUtil {
    @Autowired
    public EnvironmentConfiguration environmentConfiguration;

    public JSONObject getBaseException(Exception e) {
        BaseException baseException = new BaseException();
        baseException.setErrorCode(Constants.ERRORCODEPREFIX + e.getCause().getMessage());
        baseException.setErrorMessage(new String[]{e.getMessage()});
        if (environmentConfiguration.isDev) {
            baseException.setStackTrace(GlobalExceptionUtil.getStackTrace(e, true));
        }

        return JSONObject.parseObject(JSONObject.toJSONString(baseException), Feature.OrderedField);
    }

}
