package com.misenvios.misenviosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MisEnviosApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MisEnviosApiApplication.class, args);
    }

}
