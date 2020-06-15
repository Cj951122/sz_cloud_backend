package com.lunz.fin.config.feign;

import com.lunz.fin.config.entity.ClientStatusConfig;
import com.lunz.fin.config.entity.DictionaryVo;
import com.lunz.fin.models.WebApiResult;
import com.lunz.fin.models.ServiceApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(value = "sz-config",path = "/v1/api")
public interface IAppConfigService {

    /**
     * GetConfigByCategory
     * @param category
     * @return Data: DefaultConfigVo
     */
    @GetMapping("/kvConfig/getConfigByCategory/{category}")
    ServiceApiResult GetConfigByCategory(@PathVariable String category,
                                         @RequestParam(name="clientId",required = false) String clientId,
                                         @RequestParam(name="cloud_access",required = false) String cloudAccess);

    /**
     * getConfigByKey
     * @param key
     * @param clientId
     * @return Data: DefaultConfigVo
     */
    @GetMapping("/kvConfig/getConfigByKey/{key}")
    ServiceApiResult GetConfigByKey(@PathVariable String key,
                                    @RequestParam(name="clientId",required = false) String clientId,
                                    @RequestParam(name="cloud_access",required = false) String cloudAccess);

    @GetMapping("/sequence/getID/{code}")
    String GetID(@PathVariable String code,
                        @RequestParam(name="clientId",required = false) String clientId,
                        @RequestParam(name="cloud_access",required = false) String cloudAccess) throws Exception;

    @PostMapping("/sequence/getIDS")
    List<String> GetIDS(@RequestBody List<String> codes,
                               @RequestParam(name="clientId",required = false) String clientId,
                               @RequestParam(name="cloud_access",required = false) String cloudAccess) throws Exception;

    @PostMapping("/sequence/getIDSByMap")
    List<String> GetIDS(@RequestBody Map<String, Object> map,
                               @RequestParam(name="clientId",required = false) String clientId,
                               @RequestParam(name="cloud_access",required = false) String cloudAccess) throws Exception;

    @GetMapping("/dictionaryConfig/getDictionaryByType/{type}")
    WebApiResult<List<DictionaryVo>> GetDictionaryByType(@PathVariable("type") String type,
                                                         @RequestParam(name="clientId",required = false) String clientId,
                                                         @RequestParam(name="cloud_access",required = false) String cloudAccess);

    @GetMapping("/dictionaryConfig/getDictionaryByCode")
    WebApiResult<String> GetDictionaryByCode(@RequestParam String type, @RequestParam String code,
                                             @RequestParam(name="clientId",required = false) String clientId,
                                             @RequestParam(name="cloud_access",required = false) String cloudAccess);

    @GetMapping("/clientConfig/getClientIdByCode")
    WebApiResult<String> getClientIdByCode(@RequestParam("code") String code,
                                           @RequestParam(name="clientId",required = false) String clientId,
                                           @RequestParam(name="cloud_access",required = false) String cloudAccess);


    @GetMapping("/sequence/getApplicationNumber")
    String getApplicationNumber();

    @GetMapping("/clientConfig/getAllClientId")
    List<String> getAllClientId();

    /**
     * 获取工作流上传节点配置信息
     */
    @PostMapping("/getUploadOprConfigList")
    WebApiResult<List<ClientStatusConfig>> getUploadOprConfigList(@RequestBody ClientStatusConfig param);

    /**
     * 获取工作流上传节点配置信息(简化版)
     */
    @PostMapping("/getUploadOprConfigSimpleList")
    WebApiResult<List<String>> getUploadOprConfigSimpleList(@RequestBody ClientStatusConfig param);


    /**
     * 根据客户ID获取流程ID
     * @param clientId 客户ID
     * @param productId 产品ID
     * @return
     */
    @GetMapping("/clientConfig/getModelIdByClient")
    String getModelIdByClient(@RequestParam("clientId") String clientId,
                              @RequestParam(value = "productId",required = false) String productId);

}
