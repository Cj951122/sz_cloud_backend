package com.lunz.fin.gateway.util;

import com.lunz.fin.constant.Constants;
import com.lunz.fin.gateway.config.EnvironmentConfiguration;
import com.lunz.fin.models.ErrorWebApiResult;
import com.lunz.fin.models.WebApiResult;
import com.lunz.kernel.exceptions.util.GlobalExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Liuruixia
 * @Description:
 * @date 2019/07/23
 */
@Component
public class ExceptionUtil {

    @Autowired
    private EnvironmentConfiguration environmentConfiguration;

    public WebApiResult getBaseException(Exception e) {

        String stackTrace = "";
        if (environmentConfiguration.isDev) {
            stackTrace = GlobalExceptionUtil.getStackTrace(e, true);
        }

        return ErrorWebApiResult.error(Constants.ERRORCODEPREFIX + e.getCause().getMessage(),
                e.getMessage(),
                stackTrace);

    }
}
