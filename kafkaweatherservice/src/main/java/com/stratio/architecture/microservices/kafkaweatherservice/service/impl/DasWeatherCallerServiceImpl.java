package com.stratio.architecture.microservices.kafkaweatherservice.service.impl;

import com.stratio.architecture.microservices.kafkaweatherservice.config.AppProperties;
import com.stratio.architecture.microservices.kafkaweatherservice.dto.WeatherEntityDto;
import com.stratio.architecture.microservices.kafkaweatherservice.service.DasWeatherCallerService;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class DasWeatherCallerServiceImpl implements DasWeatherCallerService {


    private final RestTemplate restTemplate;
  private final AppProperties properties;

    private static final String protocol = "http://";

    @Override
    public CompletableFuture<List<WeatherEntityDto>> saveWeatherEntities(List<WeatherEntityDto> entities) {

        return CompletableFuture.supplyAsync(() -> retrieveSaveResponse(entities));
    }

    private List<WeatherEntityDto> retrieveSaveResponse(List<WeatherEntityDto> entities){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");

        HttpEntity<List<WeatherEntityDto>> request = new HttpEntity(entities,httpHeaders);

        ResponseEntity<WeatherEntityDto[]> result = restTemplate.exchange(
            protocol + properties.getDasWeatherServiceId()
                + ':' + properties.getDasWeatherServicePort()
                + properties.getSaveResponse(),
                HttpMethod.POST,
                request,
                WeatherEntityDto[].class);
        return Arrays.asList(result.getBody());
    }

    @Override
    public List<WeatherEntityDto> getHistoric(String city, String text) throws HttpClientErrorException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", "application/json");

        HttpEntity<Object> request = new HttpEntity(null, httpHeaders);

        String[] parameters ={city,text};

        ResponseEntity<WeatherEntityDto[]> result = restTemplate.exchange(
            protocol + properties.getDasWeatherServiceId()
                + ':' + properties.getDasWeatherServicePort()
                + properties.getGetHistoric(),
                HttpMethod.GET,
                request,
                WeatherEntityDto[].class,
                parameters);

        return Arrays.asList(result.getBody());
    }

  public DasWeatherCallerServiceImpl(RestTemplate restTemplate, AppProperties properties) {
        this.restTemplate = restTemplate;
    this.properties = properties;
    }
}
