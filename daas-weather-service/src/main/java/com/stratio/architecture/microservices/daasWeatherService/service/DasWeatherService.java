package com.stratio.architecture.microservices.daasWeatherService.service;


import com.stratio.architecture.microservices.daasWeatherService.dto.WeatherEntityDto;

import java.util.List;

public interface DasWeatherService {

    List<WeatherEntityDto> saveWeatherAudit(List<WeatherEntityDto> req) throws Exception;
    List<WeatherEntityDto> getHistoricByCityPrediction(String City, String text) throws Exception;

}
