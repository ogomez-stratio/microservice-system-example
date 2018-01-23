package com.stratio.architecture.microservices.kafkaweatherservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stratio.architecture.microservices.kafkaweatherservice.dto.WeatherEntityDto;
import com.stratio.architecture.microservices.kafkaweatherservice.dto.WeatherResponseDto;
import com.stratio.architecture.microservices.kafkaweatherservice.service.DasWeatherCallerService;
import com.stratio.architecture.microservices.kafkaweatherservice.service.MessageProcessorService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

@Service
public class MessageProcessorImpl implements MessageProcessorService {

  private final DasWeatherCallerService service;
  private final DozerBeanMapper mapper;

  @Override
  public void processMessage(String message) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    WeatherEntityDto entity = mapper
        .map(objectMapper.readValue(message, WeatherResponseDto.class), WeatherEntityDto.class);
    List<WeatherEntityDto> ret = new ArrayList<WeatherEntityDto>() {{
      add(entity);
    }};
    service.saveWeatherEntities(ret);
  }

  public MessageProcessorImpl(
      DasWeatherCallerService service, DozerBeanMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }
}
