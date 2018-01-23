package com.stratio.app.microservices.weatherService.service;

import com.stratio.app.microservices.weatherService.dto.WeatherEntityDto;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


public interface DasWeatherCallerService {

  CompletableFuture<List<WeatherEntityDto>> saveWeatherEntities(List<WeatherEntityDto> entities);

  Optional<List<WeatherEntityDto>> getHistoric(String city, String text);

}
