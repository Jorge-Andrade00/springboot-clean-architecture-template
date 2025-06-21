package com.example.ms_users_java.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class SwaggerConfig {
    // Get the build properties from pom to use in Swagger UI
    private final BuildProperties buildProperties;

    public SwaggerConfig(Optional<BuildProperties> buildProperties) {
        this.buildProperties = buildProperties.orElse(null);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        String projectName = "MS";
        String projectDescription = "Description";
        String projectVersion = "1.0.0";

        if (buildProperties != null) {
            projectName = buildProperties.getName();
            projectDescription = buildProperties.get("description") != null ? buildProperties.get("description") : projectDescription;
            projectVersion = buildProperties.getVersion();
        }

        return new OpenAPI().info(new Info()
                .title(projectName)
                .description(projectDescription)
                .version(projectVersion)
        );
    }
}
