package com.example.ms_users_java.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String swaggerTitle;

    @Value("${swagger.description}")
    private String swaggerDescription;

    @Value("${swagger.version}")
    private String swaggerVersion;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title(swaggerTitle)
                .description(swaggerDescription)
                .version(swaggerVersion)
        );
    }
}
