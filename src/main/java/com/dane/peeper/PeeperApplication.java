package com.dane.peeper;

import org.hibernate.service.spi.InjectService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeeperApplication.class, args);
    }
}