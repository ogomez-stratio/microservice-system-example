package com.stratio.app.microservices.weatherService.service;


import com.stratio.app.microservices.weatherService.dto.WeatherResponseDto;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import org.springframework.web.client.HttpClientErrorException;

public interface WeatherService {

    Optional<WeatherResponseDto> getweatherBycity(String City)
        throws HttpClientErrorException, UnsupportedEncodingException;
}
