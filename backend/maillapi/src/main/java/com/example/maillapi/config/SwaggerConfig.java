package com.example.maillapi.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
  @Bean
  public GroupedOpenApi authApi() {
    return GroupedOpenApi.builder()
        .group("그룹")
        .pathsToMatch("/api/auth/**")
        .addOpenApiCustomizer(
            openApi -> openApi.setInfo(new Info().title("board").description("게시판").version("1.0.0")))
        .build();
  }
}
