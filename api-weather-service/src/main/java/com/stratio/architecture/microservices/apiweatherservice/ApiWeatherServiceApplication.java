package com.stratio.architecture.microservices.apiweatherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ApiWeatherServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiWeatherServiceApplication.class, args);
    }
}
