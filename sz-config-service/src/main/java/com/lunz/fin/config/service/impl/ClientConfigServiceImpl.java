package com.lunz.fin.config.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.lunz.fin.entity.ServiceBase;
import com.lunz.fin.config.entity.domain.ClientConfig;
import com.lunz.fin.config.service.interfaces.IClientConfigService;
import com.lunz.fin.config.mapper.ClientConfigMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author al
 * @date 2019/7/31 11:06
 * @description 获取客户ID 配置信息 - DB
 */
@Service
public class ClientConfigServiceImpl extends ServiceBase<ClientConfigMapper,ClientConfig> implements IClientConfigService {


    @Override
    public ClientConfig getClientConfigByClient(String clientId) {
        return baseMapper.selectById(clientId);
    }

    @Override
    public ClientConfig getClientIdByCode(String code) {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setCode(code);
        return baseMapper.selectOne(clientConfig);
    }

    @Override
    public List<String> getAllClientId() {
        Wrapper<ClientConfig> wrapper = new EntityWrapper<>();
        wrapper.isNotNull("Id");
        List<ClientConfig> clientConfigLst = baseMapper.selectList(wrapper);
        List<String> clientIdLst = clientConfigLst.stream().map(p -> p.getId()).collect(Collectors.toList());
        if(clientIdLst != null && clientIdLst.size() > 0){
            return clientIdLst;
        }
        return null;
    }
}

