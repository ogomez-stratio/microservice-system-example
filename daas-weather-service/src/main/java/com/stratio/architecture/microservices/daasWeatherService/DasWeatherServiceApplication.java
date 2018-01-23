package com.stratio.architecture.microservices.daasWeatherService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class DasWeatherServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DasWeatherServiceApplication.class, args);
  }

}
