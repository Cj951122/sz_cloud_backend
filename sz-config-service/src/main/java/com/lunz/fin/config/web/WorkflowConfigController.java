package com.lunz.fin.config.web;

import com.lunz.fin.config.service.interfaces.IWorkflowConfigService;
import com.lunz.fin.models.WebApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;

/**
 * @author al
 * @date 2020/3/11 13:54
 * @description
 */
@RestController
@Slf4j
@Api(value = "/WorkflowConfig", tags = "工作流配置信息")
public class WorkflowConfigController {

    @Autowired
    IWorkflowConfigService workflowConfigService;

    @ApiOperation("测试功能")
    @PostMapping("/test")
    public WebApiResult<String> getUploadOprConfigList() {
//        throw new BusinessException(BizExceptionEnum.BAD_LICENCE_TYPE);
        throw new ConstraintViolationException(new HashSet<>());

    }

}
