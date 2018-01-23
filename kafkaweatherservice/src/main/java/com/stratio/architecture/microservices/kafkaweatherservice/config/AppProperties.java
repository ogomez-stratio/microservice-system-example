package com.stratio.architecture.microservices.kafkaweatherservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {

  private Kafka kafka;
  private String dasWeatherServiceId;
  private String dasWeatherServicePort;
  private String saveResponse;
  private String getHistoric;

  @Data
  public static class Kafka {

    private String topic;
    private String bootstrapServers;
    private String consumerGroup;
  }
}
