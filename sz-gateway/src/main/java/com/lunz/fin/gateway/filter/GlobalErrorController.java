package com.lunz.fin.gateway.filter;

import com.lunz.fin.gateway.util.ExceptionUtil;
import com.lunz.fin.models.ErrorWebApiResult;
import com.lunz.kernel.exceptions.NotFoundResourceException;
import com.lunz.kernel.exceptions.NotKnownException;
import com.lunz.kernel.exceptions.base.ThridServiceException;
import com.lunz.kernel.exceptions.core.ErrorStatusConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Liuruixia
 * @Description:
 * @date 2019/06/28
 */
@Component
@Slf4j
public class GlobalErrorController extends BasicErrorController {

    public GlobalErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);

    }

    @Autowired
    private ExceptionUtil exceptionUtil;

    @RequestMapping
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {

        Map<String, Object> body = getErrorAttributes(request, false);
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(body, status);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean isIncludeStackTrace) {
        HttpStatus status = super.getStatus(request);
        ErrorWebApiResult webApiResult;
        Exception exception;
        if (status.value() == HttpStatus.NOT_FOUND.value()) {
            exception = new NotFoundResourceException();
        } else if (status.value() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            exception = new ThridServiceException(ErrorStatusConstants.HTTP_REQUEST_ERROR);
        } else {
            exception = new NotKnownException();

        }
        webApiResult = (ErrorWebApiResult)exceptionUtil.getBaseException(exception);

        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("code", webApiResult.getCode());
        result.put("message", webApiResult.getMessage());
        result.put("errorMessage", webApiResult.getErrorMessages());
        return result;
    }

}
