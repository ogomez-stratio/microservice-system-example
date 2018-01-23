package com.stratio.app.microservices.apiweatherservice.service;

import com.stratio.app.microservices.apiweatherservice.dto.WeatherEntityDto;
import com.stratio.app.microservices.apiweatherservice.dto.WeatherResponseDto;
import java.util.List;

public interface ApiCallerService {

    WeatherResponseDto getPrediction(String city);

    List<WeatherEntityDto> getPredictionHistory(String city, String prediction);
}
