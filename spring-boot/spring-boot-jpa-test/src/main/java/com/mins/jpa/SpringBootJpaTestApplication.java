package com.mins.jpa;

import com.mins.jpa.domain.User;
import com.mins.jpa.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

/**
 * @author 小铭
 */
@SpringBootApplication
public class SpringBootJpaTestApplication {

    @Autowired
    private UserRepository userRepository;

    @Component
    class DataSetUp implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) {
            userRepository.save(User.builder().username("zheng").password("Mins1205").build());
            userRepository.save(User.builder().username("mins").password("Mins1205").build());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJpaTestApplication.class, args);
    }
}
