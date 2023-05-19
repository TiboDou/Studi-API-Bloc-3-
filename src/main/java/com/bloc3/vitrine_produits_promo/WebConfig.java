package com.bloc3.vitrine_produits_promo;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig {

    public static void main(String[] args) {
        SpringApplication.run(WebConfig.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/produits").allowedOrigins("https://studi-bloc3-front-td.herokuapp.com");
            }
        };
    }

}
