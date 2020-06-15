package com.lunz.fin.config.service.impl;

import com.lunz.fin.config.mapper.WorkflowConfigMapper;
import com.lunz.fin.config.service.interfaces.IWorkflowConfigService;
import com.lunz.fin.entity.ServiceBase;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author al
 * @date 2019/8/5 13:06
 * @description 节点提交操作配置项
 */
@Service
public class WorkflowConfigServiceImpl extends ServiceBase<WorkflowConfigMapper,String> implements IWorkflowConfigService {

    @Override
    public List<String> getUploadOprConfigList(String param) {
        return baseMapper.getUploadOprConfigList(param);
    }
}
