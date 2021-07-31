package com.sparta.instagram_clone_sv.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// CORS 허용하는 부분 //
// 클라이언트 사이드에서 localhost:3000을 구현하면, 그 곳에서 이 서버의 api 활용 가능하도록 허용하는 부분 //
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("POST", "GET", "PUT", "DELETE", "HEAD", "OPTIONS")
                .allowCredentials(true);
    }
}
