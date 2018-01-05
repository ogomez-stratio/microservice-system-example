package com.stratio.architecture.microservices.apiweatherservice.service;

import com.stratio.architecture.microservices.apiweatherservice.dto.WeatherEntityDto;
import com.stratio.architecture.microservices.apiweatherservice.dto.WeatherResponseDto;

import java.util.List;

public interface ApiCallerService {

    WeatherResponseDto getPrediction(String city);

    List<WeatherEntityDto> getPredictionHistory(String city, String prediction);
}
