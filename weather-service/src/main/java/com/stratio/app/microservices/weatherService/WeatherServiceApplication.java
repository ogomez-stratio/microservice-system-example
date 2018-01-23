package com.stratio.app.microservices.weatherService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringBootApplication
@EnableAutoConfiguration
@EnableFeignClients
public class WeatherServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(WeatherServiceApplication.class, args);
  }
}
