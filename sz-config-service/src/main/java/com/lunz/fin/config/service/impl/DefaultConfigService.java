package com.lunz.fin.config.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lunz.fin.config.conf.redis.RedisTemplateUtil;
import com.lunz.fin.config.entity.domain.DefaultConfig;
import com.lunz.fin.config.entity.domain.DtoEStage;
import com.lunz.fin.entity.ServiceBase;
import com.lunz.fin.constant.MdcConstant;
import com.lunz.fin.config.service.interfaces.IClientConfigService;
import com.lunz.fin.config.service.interfaces.IDefaultConfigService;
import com.lunz.fin.config.mapper.DefaultConfigMapper;
import com.lunz.fin.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DefaultConfigService extends ServiceBase<DefaultConfigMapper, DefaultConfig> implements IDefaultConfigService {

    @Autowired
    IDefaultConfigService iDefaultConfigService;

    @Autowired
    RedisTemplateUtil redis;

    @Autowired
    IClientConfigService clientConfigService;


    @Override
    public List<DefaultConfig> GetDefaultConfigs() {
        String clientId = MDC.get(MdcConstant.CLIENTID);
        String appKey = MDC.get(MdcConstant.APPKEY);
        EntityWrapper<DefaultConfig> wrapper = new EntityWrapper<>();
        if(!this.IsValidClientId(clientId)){
            clientId = "default";
        }
        String allStr = redis.hget("CONFIG_KEY", "all_" + appKey + "_" + clientId);
        if(!CommonUtil.isNullStr(allStr)&&allStr.length()>2){
            return JSON.parseArray(allStr,DefaultConfig.class);
        }
        if(!CommonUtil.isNullStr(clientId) && !clientId.equals("")){
            wrapper.eq("ClientId", clientId);
        }
        wrapper.eq("AppKey", appKey);
        List<DefaultConfig> dicLst = baseMapper.selectList(wrapper);
        redis.hset("CONFIG_KEY", "all_" + appKey + "_" + clientId,dicLst,600);
        return dicLst;
    }


    /*
     *
     * Get Configs By Key
     *
     * */
    @Override
    public List<DefaultConfig> GetDefaultConfigByKey(String key) {
        String clientId= MDC.get(MdcConstant.CLIENTID);
        List<DefaultConfig> allList = iDefaultConfigService.GetDefaultConfigs();
        return allList.stream().filter((DefaultConfig p) -> p.getKey().equals(key)).collect(Collectors.toList());
    }

    @Override
    public DtoEStage GetEStageDto() {
        DtoEStage dtoEStage = new DtoEStage();
        String clientId  = MDC.get(MdcConstant.CLIENTID);
        String appKey = MDC.get(MdcConstant.APPKEY);
        EntityWrapper<DefaultConfig> wrapper = new EntityWrapper<>();
        if(!this.IsValidClientId(clientId)){
            clientId = "default";
        }
        String eStageStr = redis.hget("CONFIG_KEY", "eStage_"+appKey + "_" +clientId);
        if(!CommonUtil.isNullStr(eStageStr)&&eStageStr.length()>2){
            return JSON.parseObject(eStageStr,DtoEStage.class);
        }

        wrapper.eq("Category", "estage");
        wrapper.eq("AppKey", appKey);
        if(!CommonUtil.isNullStr(clientId) && !clientId.equals("")){
            wrapper.eq("ClientId", clientId);
        }
        List<DefaultConfig> lst = baseMapper.selectList(wrapper);

        dtoEStage.setPublicKey(lst.stream().filter(s -> s.getKey().equals("publicKey")).collect(Collectors.toList()).get(0).getValue());
        dtoEStage.setPrivateKey(lst.stream().filter(s -> s.getKey().equals("privateKey")).collect(Collectors.toList()).get(0).getValue());
        dtoEStage.setAssureNo(lst.stream().filter(s -> s.getKey().equals("assureNo")).collect(Collectors.toList()).get(0).getValue());
        dtoEStage.setBankCode(lst.stream().filter(s -> s.getKey().equals("bankCode")).collect(Collectors.toList()).get(0).getValue());
        dtoEStage.setPlatNo(lst.stream().filter(s -> s.getKey().equals("platNo")).collect(Collectors.toList()).get(0).getValue());
        dtoEStage.setBankType(lst.stream().filter(s -> s.getKey().equals("bankType")).collect(Collectors.toList()).get(0).getValue());
        dtoEStage.setProductType(lst.stream().filter(s -> s.getKey().equals("productType")).collect(Collectors.toList()).get(0).getValue());
        dtoEStage.setCocomId(lst.stream().filter(s -> s.getKey().equals("cocomId")).collect(Collectors.toList()).get(0).getValue());
        dtoEStage.setMarketingArchivesNum(lst.stream().filter(s -> s.getKey().equals("marketingArchivesNum")).collect(Collectors.toList()).get(0).getValue());
        dtoEStage.setUrl(lst.stream().filter(s -> s.getKey().equals("url")).collect(Collectors.toList()).get(0).getValue());
        dtoEStage.setEsburl(lst.stream().filter(s -> s.getKey().equals("esburl")).collect(Collectors.toList()).get(0).getValue());
        dtoEStage.setSalerRoleID(lst.stream().filter(s -> s.getKey().equals("SalerRoleID")).collect(Collectors.toList()).get(0).getValue());
        dtoEStage.setFinSpecRoleID(lst.stream().filter(s -> s.getKey().equals("FinSpecRoleID")).collect(Collectors.toList()).get(0).getValue());
        dtoEStage.setPreAuditRoleID(lst.stream().filter(s -> s.getKey().equals("PreAuditRoleID")).collect(Collectors.toList()).get(0).getValue());
        dtoEStage.setIsCheckCredit(lst.stream().filter(s -> s.getKey().equals("isCheckCredit")).collect(Collectors.toList()).get(0).getValue().equals("0") ? false : true);
        dtoEStage.setIsApplyCard(lst.stream().filter(s -> s.getKey().equals("isApplyCard")).collect(Collectors.toList()).get(0).getValue().equals("0") ? false : true);
        dtoEStage.setIsApplyStage(lst.stream().filter(s -> s.getKey().equals("isApplyStage")).collect(Collectors.toList()).get(0).getValue().equals("0") ? false : true);

        redis.hset("CONFIG_KEY","eStage_"+appKey + "_" +clientId, dtoEStage,600);
        return dtoEStage;
    }

//    @Override
//    public String GetReferByKey(String key) {
//        CacheObject cacheObject = cache.get("CONFIG_KEY", key);
//        if (cacheObject.getValue() != null) {
//            return (String) cacheObject.getValue();
//        }
//        DefaultConfig defaultConfig = new DefaultConfig();
//        defaultConfig.setKey(key);
//        String value = baseMapper.selectOne(defaultConfig).getValue();
//        cache.set("CONFIG_KEY",key,value);
//        return value;
//    }

    @Override
    public List<DefaultConfig> GetConfigByCategory(String category) {

        String clientId  = MDC.get(MdcConstant.CLIENTID);
        String appKey = MDC.get(MdcConstant.APPKEY);
        EntityWrapper<DefaultConfig> wrapper = new EntityWrapper<>();
        if(!this.IsValidClientId(clientId)){
            clientId = "default";
        }
        String categoryStr = redis.hget("CONFIG_KEY", category+"_"+appKey + "_" +clientId);
        if(!CommonUtil.isNullStr(categoryStr)&&categoryStr.length()>2){
            List<DefaultConfig> defaultConfigLst = JSON.parseArray(categoryStr,DefaultConfig.class);
            return defaultConfigLst;
        }
        wrapper.eq("Category", category);
        wrapper.eq("AppKey", appKey);
        if(!CommonUtil.isNullStr(clientId) && !clientId.equals("")){
            wrapper.eq("ClientId", clientId);
        }
        List<DefaultConfig> defaultConfigLst = baseMapper.selectList(wrapper);
        if(defaultConfigLst.size() >= 1){
            redis.hset("CONFIG_KEY", category+"_"+appKey + "_" +clientId, defaultConfigLst,600);
        }
        return defaultConfigLst;
    }

    public boolean IsValidClientId(String clientId) {
        List<String> allClientIdLst = clientConfigService.getAllClientId();
        if(allClientIdLst != null && allClientIdLst.size() > 0){
            return allClientIdLst.contains(clientId);
        }
        return false;
    }
}
