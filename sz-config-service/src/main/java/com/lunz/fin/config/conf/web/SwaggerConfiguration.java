package com.lunz.fin.config.conf.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Liuruixia
 * @Description: Swagger配置
 * @date 2019/06/13
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${config.oauth2.accessTokenUri}")
    private String accessTokenUri;

    @Value("${config.oauth2.swagger.title}")
    private String swaggerTitle;

    @Value("${config.oauth2.swagger.description}")
    private String swaggerDescription;

    @Value("${config.oauth2.swagger.version}")
    private String swaggerVersion;

    @Value("${config.oauth2.swagger.package}")
    private String swaggerPackage;

    @Bean
    public Docket createRestApi() {

        ParameterBuilder tokenBuilder = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenBuilder.name("token")
                .defaultValue("")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        pars.add(tokenBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(swaggerPackage)).paths(PathSelectors.any())
//                .build().globalOperationParameters(pars)
                .build().securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Arrays.asList(securitySchema()))
                .apiInfo(apiInfo());

    }

    private OAuth securitySchema() {

        List<AuthorizationScope> authorizationScopeList = new ArrayList();
        List<GrantType> grantTypes = new ArrayList();
        GrantType passwordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(accessTokenUri);
        grantTypes.add(passwordCredentialsGrant);

        return new OAuth("oauth2", authorizationScopeList, grantTypes);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {

        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("read", "read all");
        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
        authorizationScopes[2] = new AuthorizationScope("write", "write all");

        return Collections.singletonList(new SecurityReference("oauth2", authorizationScopes));
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder().build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(swaggerTitle).version(swaggerVersion).description(swaggerDescription)
                .build();
    }
}
