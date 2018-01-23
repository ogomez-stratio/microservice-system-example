package com.stratio.app.microservices.apiweatherservice.service.impl;

import com.stratio.app.microservices.apiweatherservice.client.WeatherServiceClient;
import com.stratio.app.microservices.apiweatherservice.dto.WeatherEntityDto;
import com.stratio.app.microservices.apiweatherservice.dto.WeatherResponseDto;
import com.stratio.app.microservices.apiweatherservice.service.ApiCallerService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
class ApiCallerServiceImpl implements ApiCallerService {

  private final WeatherServiceClient weatherServiceClient;

  ApiCallerServiceImpl(
      WeatherServiceClient weatherServiceClient) {
    this.weatherServiceClient = weatherServiceClient;
  }


  @Override
  public WeatherResponseDto getPrediction(String city)
      throws HttpClientErrorException {
    return weatherServiceClient.getPrediction(city);
  }

  @Override
  public List<WeatherEntityDto> getPredictionHistory(String city, String prediction)
      throws HttpClientErrorException {
    return weatherServiceClient.getPredictionHistory(city, prediction);
  }

}
