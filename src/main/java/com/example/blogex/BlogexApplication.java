package com.example.blogex;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.mapstruct.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@OpenAPIDefinition
@SpringBootApplication
@EnableJpaAuditing
public class BlogexApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogexApplication.class, args);
    }

}
