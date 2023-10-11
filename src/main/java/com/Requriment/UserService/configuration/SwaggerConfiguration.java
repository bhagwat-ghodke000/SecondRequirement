package com.Requriment.UserService.configuration;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
@Configuration
public class SwaggerConfiguration {

    public Docket apiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.Requriment.UserService"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo getInfo() {
        // TODO Auto-generated method stub
        return new ApiInfo("Second Requriment : Backend Course", "This project is devolpe by Bhagwat Ghodke", "1.0", "Terms of Service", "License Of Apis", "Licensc", "Collections");
    }
}
