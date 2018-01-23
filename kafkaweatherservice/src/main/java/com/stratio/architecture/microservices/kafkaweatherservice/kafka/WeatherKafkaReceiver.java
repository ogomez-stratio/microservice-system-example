package com.stratio.architecture.microservices.kafkaweatherservice.kafka;

import com.stratio.architecture.microservices.kafkaweatherservice.service.MessageProcessorService;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
@Slf4j
public class WeatherKafkaReceiver {

  private final MessageProcessorService processor;


  @StreamListener(Sink.INPUT)
  public void receive(String message) throws IOException {
    log.info("received message='{}'", message);
    processor.processMessage(message);
  }

  public WeatherKafkaReceiver(
      MessageProcessorService processor) {
    this.processor = processor;
  }
}
