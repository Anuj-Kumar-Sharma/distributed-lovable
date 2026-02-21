package com.codingshuttle.distributed_lovable.common_lib.security;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerExceptionResolver;

@AutoConfiguration
public class SharedSecurityAutoConfiguration {

    @Bean
    public AuthUtil authUtil() {
        return new AuthUtil();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(AuthUtil authUtil, HandlerExceptionResolver handlerExceptionResolver) {
        return new JwtAuthFilter(authUtil, handlerExceptionResolver);
    }
}
