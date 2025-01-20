package com.gulowsen.vegvesenproxy.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("1 public-apis")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    GroupedOpenApi actuatorApi() {
        return GroupedOpenApi.builder()
                .group("2 actuators")
                .pathsToMatch("/actuator/**")
                .build();
    }



    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("SVV Proxy Application").version("1.0.0"));
    }



}
