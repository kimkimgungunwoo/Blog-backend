package com.example.blogex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BlogexApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogexApplication.class, args);
    }

}
