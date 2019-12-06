package com.ctgpaas.webfront;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ConditionalOnClass({EnableSwagger2.class})
@ConditionalOnProperty({"swagger.basePackage"})
@EnableConfigurationProperties({SwaggerProperties.class})
public class SwaggerAutoConfiguration {
    public SwaggerAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean(
    name = {"swaggerDocket"}
    )
    public Docket swaggerDocket(SwaggerProperties swaggerProperties) {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        String groupName = swaggerProperties.getGroup();
        if(!StringUtils.isEmpty(groupName)) {
            docket.groupName(groupName);
        }
        return docket.apiInfo((new ApiInfoBuilder()).title(swaggerProperties.getTitle()).description(swaggerProperties.getDescription()).version(swaggerProperties.getVersion()).build()).select().apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage())).paths(PathSelectors.any()).build();
    }

    @Bean
    @ConditionalOnProperty({"swagger.openApi.basePackage"})
    @ConditionalOnMissingBean(
    name = {"swaggerOpenApiDocket"}
    )
    public Docket swaggerOpenApiDocket(SwaggerProperties swaggerProperties) {
        SwaggerProperties openApiProps = swaggerProperties.getOpenApi();
        String groupName = openApiProps.getGroup();
        String title = openApiProps.getTitle();
        String description = openApiProps.getDescription();
        String version = openApiProps.getVersion();
        return (new Docket(DocumentationType.SWAGGER_2)).groupName(StringUtils.isEmpty(groupName)?"OpenApi":groupName).apiInfo((new ApiInfoBuilder()).title(StringUtils.isEmpty(title)?swaggerProperties.getTitle():title).description(StringUtils.isEmpty(description)?swaggerProperties.getDescription():description).version(StringUtils.isEmpty(version)?swaggerProperties.getVersion():version).build()).select().apis(RequestHandlerSelectors.basePackage(openApiProps.getBasePackage())).paths(PathSelectors.any()).build();
    }
}
