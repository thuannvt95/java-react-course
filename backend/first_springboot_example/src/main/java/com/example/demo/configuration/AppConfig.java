package com.example.demo.configuration;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.crypto.SecretKey;
import java.util.logging.Logger;

@Configuration
@Slf4j
public class AppConfig {
    @Autowired
    ResourceLoader resourceLoader;

    private Logger logger = Logger.getLogger(AppConfig.class.getName());

    @Bean
    public SecretKey jwtSecretKey() {
        try {
            Resource resource = resourceLoader.getResource("classpath:secrets/jwt.secret");
            String secret = new String(resource.getInputStream().readAllBytes()).trim();
            return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret)
        );
        } catch (Exception e) {
            logger.info("Error loading JWT secret key: " + e.getMessage());
        }
        return null;
    }
}
