package com.lunz.fin.config.service.interfaces;

import com.lunz.fin.config.entity.domain.DtoEStage;
import com.lunz.fin.entity.IServiceBase;
import com.lunz.fin.config.entity.domain.DefaultConfig;


import java.util.List;

public interface IDefaultConfigService extends IServiceBase<DefaultConfig> {

    public List<DefaultConfig> GetDefaultConfigs();

    public  List<DefaultConfig> GetDefaultConfigByKey(String key);

    DtoEStage GetEStageDto();

    List<DefaultConfig> GetConfigByCategory(String category);
}
