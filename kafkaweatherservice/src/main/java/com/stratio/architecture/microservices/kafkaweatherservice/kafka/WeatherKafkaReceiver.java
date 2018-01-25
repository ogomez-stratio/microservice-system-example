package com.stratio.architecture.microservices.kafkaweatherservice.kafka;

import com.stratio.architecture.microservices.kafkaweatherservice.service.MessageProcessorService;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
@Slf4j
public class WeatherKafkaReceiver {

  private final MessageProcessorService processor;

  @KafkaListener(topics = "${scd.kafka.topic}")
  public void receive(String message) throws IOException {
    log.info("received message='{}'", message);
    processor.processMessage(message);
  }

  public WeatherKafkaReceiver(
      MessageProcessorService processor) {
    this.processor = processor;
  }
}
