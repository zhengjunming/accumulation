package com.mins;

import com.mins.model.StorageProperties;
import com.mins.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author 小铭
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpringBootFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFileApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return(args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
