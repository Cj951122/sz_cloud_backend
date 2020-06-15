package com.lunz.fin.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.lunz.fin.constant.Constants;
import com.lunz.fin.exception.BusinessException;
import com.lunz.fin.models.ErrorWebApiResult;
import com.lunz.fin.models.WebApiResult;
import com.lunz.kernel.exceptions.ValidationException;
import com.lunz.kernel.exceptions.core.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.lunz.kernel.exceptions.util.GlobalExceptionUtil.getExceptionMessage;


/**
 * 异常处理考虑去掉try catch,直接抛出异常
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 * @author zy
 */
@Slf4j
@Component
@ControllerAdvice(basePackages = "com.lunz.fin")
public class GlobalExceptionHandler {

    @Autowired
    private ExceptionResultUtil exceptionResultUtil;
    @Autowired
    public EnvironmentConfiguration environmentConfiguration;


    /**
     * 业务异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public WebApiResult handleBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return ErrorWebApiResult.error(e.getCode(), e.getMessage());
    }


    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public WebApiResult handleConstraintViolationException(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();

        List<String> messages = constraintViolations.stream()
                .map(constraintViolation -> String.format(" '%s' %s",
                        constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
                .distinct().collect(Collectors.toList());

        JSONObject exceptionResult = exceptionResultUtil.getBaseException(
                new ValidationException(StringUtils.join(messages, ",")));

        return ErrorWebApiResult.error(HttpStatus.BAD_REQUEST.value(), ""
                , exceptionResult.toJSONString());
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public WebApiResult handleRuntimeException(RuntimeException exception) {
        boolean isDev = environmentConfiguration.isDev;
        String prefix = getPrefix();

        Pair<BaseException, Integer> exceptionMessage = getExceptionMessage(exception, isDev, prefix, null);
        Object messageObject = exceptionMessage.getLeft();
        JSONObject exceptionResult = JSONObject.parseObject(JSONObject.toJSONString(messageObject), Feature.OrderedField);

        return ErrorWebApiResult.error(exceptionMessage.getRight().intValue(), ""
                , exceptionResult.toJSONString());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public WebApiResult handleException(Exception exception) {
        return ErrorWebApiResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ""
                , "");
    }


    /**
     * 参数校验错误
     *
     * @author fengshuonan
     * @Date 2020/2/5 11:50 下午
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public WebApiResult handleError(MissingServletRequestParameterException e) {
        log.warn("Missing Request Parameter", e);
        String message = String.format("Missing Request Parameter: %s", e.getParameterName());
        return ErrorWebApiResult.error(HttpStatus.BAD_REQUEST.value(), message
                , "");
    }


    /**
     * 参数校验错误
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public WebApiResult handleError(MethodArgumentTypeMismatchException e) {
        log.warn("Method Argument Type Mismatch", e);
        String message = String.format("Method Argument Type Mismatch: %s", e.getName());
        return ErrorWebApiResult.error(HttpStatus.BAD_REQUEST.value(), message
                , "");
    }


    /**
     * 参数校验错误
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public WebApiResult handleError(MethodArgumentNotValidException e) {
        log.warn("Method Argument Not Valid", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return ErrorWebApiResult.error(HttpStatus.BAD_REQUEST.value(), message
                , "");
    }


    /**
     * 参数绑定错误异常
     *
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public WebApiResult handleError(BindException e) {
        log.warn("Bind Exception", e);
        FieldError error = e.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return ErrorWebApiResult.error(HttpStatus.BAD_REQUEST.value(), message
                , "");
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public WebApiResult handleError(HttpMessageNotReadableException e) {
        log.warn("HttpMessageNotReadableException ", e);
        String message = String.format("HttpMessageNotReadableException:%s", e.getMessage());
        return ErrorWebApiResult.error(HttpStatus.BAD_REQUEST.value(), message
                , "");
    }


    /**
     * 拦截未知的运行时异常，返回状态码500
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public WebApiResult serverError(Throwable e) {
        log.error("运行时异常:", e);
        String message = String.format("服务器未知运行时异常: %s", e.getMessage());
        return ErrorWebApiResult.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message
                , "");
    }


    private String getPrefix() {
        return Constants.ERRORCODEPREFIX;
    }


}
