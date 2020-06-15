package com.lunz.fin.gateway.config;

import com.lunz.fin.gateway.constant.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liuruixia
 * @Description: swagger聚合配置
 * @date 2019/06/23
 */
@Configuration
@Primary
@EnableConfigurationProperties(ZuulProperties.class)
@EnableSwagger2
public class SwaggerResourceProvider implements SwaggerResourcesProvider {
    @Autowired
    ZuulProperties properties;
    @Value("${config.oauth2.swagger.version}")
    private String swaggerVersion;

    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList();
        properties.getRoutes().values().stream()
                .forEach(route -> resources.add(
                        resource(route.getId(), "/v1/api" + route.getPath().replace("**", "v2/" + CommonConstants.SWAGGER_DOC_PATH), swaggerVersion)
                ));
        return resources;
    }


    private SwaggerResource resource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
