package dev.innocentudo.librarymanagementsystem.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("library-management")
                .pathsToMatch("/api/books/**", "/api/borrowers/**")
                .build();
    }

    @Bean
    public io.swagger.v3.oas.models.OpenAPI customOpenAPI() {
        return new io.swagger.v3.oas.models.OpenAPI()
                .info(new Info()
                        .title("Library Management System API")
                        .version("1.0")
                        .description("API documentation for the Library Management System")
                        .contact(new Contact().name("Innocent Udo").email("innocentcharlesudo@gmail.com"))
                );
    }
}
