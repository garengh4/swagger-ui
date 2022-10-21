package com.world.hello.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.world.hello"))
        .paths(regex("/api.*"))
        .build()
        .apiInfo(metaData());
  }

  private ApiInfo metaData() {
    return new ApiInfo(
        "Spring Boot REST API",
        "Spring Boot REST API for User management",
        "1.0",
        "Terms of service",
        new Contact("Hello", "https://helloworld.com", "hello@world.com"),
        "Apache License Version 2.0",
        "https://www.apache.org/licenses/LICENSE-2.0",
        new ArrayList<>());
  }
}
