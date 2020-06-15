package com.lunz.fin.config.service.interfaces;

import com.lunz.fin.entity.IServiceBase;
import com.lunz.fin.config.entity.domain.ClientConfig;

import java.util.List;

/**
 * @author al
 * @date 2019/7/31 10:58
 * @description
 */
public interface IClientConfigService extends IServiceBase<ClientConfig> {

    ClientConfig getClientConfigByClient(String clientId);

    ClientConfig getClientIdByCode(String code);

    List<String> getAllClientId();

}
