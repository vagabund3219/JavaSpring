package com.example.secondlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
@SpringBootApplication
public class SecondLabApplication implements WebMvcConfigurer{

    public static void main(String[] args) {
        SpringApplication.run(SecondLabApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/book").setViewName("book");
        registry.addViewController("/author").setViewName("author");
    }


}
