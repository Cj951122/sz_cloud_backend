package com.lunz.fin.config.service.interfaces;

import com.lunz.fin.entity.IServiceBase;

import java.util.List;

/**
 * @author al
 * @date 2019/8/5 13:04
 * @description 节点提交操作配置项
 */
public interface IWorkflowConfigService extends IServiceBase<String> {

    List<String> getUploadOprConfigList(String param);

}
