package com.stratio.architecture.microservices.kafkaweatherservice.service;

import com.stratio.architecture.microservices.kafkaweatherservice.dto.WeatherEntityDto;
import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface DasWeatherCallerService {

    CompletableFuture<List<WeatherEntityDto>> saveWeatherEntities(List<WeatherEntityDto> entities);
    List<WeatherEntityDto> getHistoric(String city, String text);

}
