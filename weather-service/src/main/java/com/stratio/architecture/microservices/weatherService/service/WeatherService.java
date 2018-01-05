package com.stratio.architecture.microservices.weatherService.service;


import com.stratio.architecture.microservices.weatherService.dto.WeatherResponseDto;
import org.springframework.web.client.HttpClientErrorException;

public interface WeatherService {

    WeatherResponseDto getweatherBycity(String City) throws HttpClientErrorException;
}
