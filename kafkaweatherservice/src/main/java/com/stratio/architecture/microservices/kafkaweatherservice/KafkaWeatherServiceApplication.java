package com.stratio.architecture.microservices.kafkaweatherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class KafkaWeatherServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaWeatherServiceApplication.class, args);
  }

}
