package com.example.kvjp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class KvjpApartmentMgnApplication {

    public static void main(String[] args) {
        SpringApplication.run(KvjpApartmentMgnApplication.class, args);
        ;
    }

}
