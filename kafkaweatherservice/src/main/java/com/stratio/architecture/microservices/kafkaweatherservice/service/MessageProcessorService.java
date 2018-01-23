package com.stratio.architecture.microservices.kafkaweatherservice.service;

import java.io.IOException;

public interface MessageProcessorService {

  void processMessage(String message) throws IOException;
}
