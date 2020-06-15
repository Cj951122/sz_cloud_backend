package com.lunz.fin.config.feign.hystrix;

import com.lunz.fin.config.entity.ClientStatusConfig;
import com.lunz.fin.config.entity.DictionaryVo;
import com.lunz.fin.config.feign.IAppConfigService;
import com.lunz.fin.models.WebApiResult;
import com.lunz.fin.models.ServiceApiResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author al
 * @date 2020/2/14 16:38
 * @description 熔断实现类：理论上应该放在服务消费者包里面，各自的消费者应有各自的熔断定义（因为涉及到自己的业务逻辑）
 */
@Component
public class ConfigFallbackImpl implements IAppConfigService {

    /**
     * GetConfigByCategory
     *
     * @param category
     * @return DefaultConfigVo
     */
    @Override
    public ServiceApiResult GetConfigByCategory(String category, String clientId, String cloudAccess) {

        return ServiceApiResult.error(200,"获取配置信息失败！");
    }

    /**
     * GetConfigByCategory
     *
     * @param key
     * @param clientId
     * @return Data: DefaultConfigVo
     */
    @Override
    public ServiceApiResult GetConfigByKey(String key, String clientId, String cloudAccess) {

        return ServiceApiResult.error(200,"获取配置信息失败！");
    }

    @Override
    public String GetID(String code, String clientId, String cloudAccess) throws Exception{

        throw new Exception("配置服务器出现异常，获取ID失败");
    }

    @Override
    public List<String> GetIDS(List<String> codes, String clientId, String cloudAccess) throws Exception{

        throw new Exception("配置服务器出现异常，获取ID失败");
    }

    @Override
    public List<String> GetIDS(Map<String, Object> map, String clientId, String cloudAccess) throws Exception{

        throw new Exception("配置服务器出现异常，获取ID失败");
    }

    @Override
    public WebApiResult<List<DictionaryVo>> GetDictionaryByType(String type, String clientId, String cloudAccess) {

        WebApiResult<List<DictionaryVo>> result = new WebApiResult<>();
        result.setMessage("根据类型获取字典表配置异常！"+"配置服务器出现异常");
        result.setSuccess(false);
        return result;
    }

    @Override
    public WebApiResult<String> GetDictionaryByCode(String type, String code, String clientId, String cloudAccess) {

        return WebApiResult.error("根据类型和代码获取字典表配置异常！"+"配置服务器出现异常");
    }

    @Override
    public WebApiResult<String> getClientIdByCode(String code, String clientId, String cloudAccess) {

        return WebApiResult.error("根据客户标识码获取客户配置信息请求异常！"+"配置服务器出现异常");
    }

    @Override
    public String getApplicationNumber() {
        return "获取ApplicationNumber异常";
    }

    @Override
    public List<String> getAllClientId() {
        return null;
    }

    @Override
    public WebApiResult<List<ClientStatusConfig>> getUploadOprConfigList(ClientStatusConfig param) {
        return null;
    }

    @Override
    public WebApiResult<List<String>> getUploadOprConfigSimpleList(ClientStatusConfig param) {
        return null;
    }

    @Override
    public String getModelIdByClient(String clientId, String productId) {
        return null;
    }
}
