package com.example.servingwebcontent;

import com.example.servingwebcontent.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ApplicationConfig.class)
public class ServingWebContentApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServingWebContentApplication.class);
    }

}
