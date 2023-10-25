package com.gaseng.global.config;

import com.gaseng.global.annotation.ExtractPayloadArgumentResolver;
import com.gaseng.global.annotation.ExtractTokenArgumentResolver;
import com.gaseng.jwt.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final JwtTokenProvider jwtTokenProvider;
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
          .allowedOrigins("https://gaseng.web.app");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ExtractTokenArgumentResolver(jwtTokenProvider));
        resolvers.add(new ExtractPayloadArgumentResolver(jwtTokenProvider));
    }
}