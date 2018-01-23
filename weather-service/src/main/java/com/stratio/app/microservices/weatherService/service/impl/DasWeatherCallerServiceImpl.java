package com.stratio.app.microservices.weatherService.service.impl;

import com.stratio.app.microservices.weatherService.client.WeatherAuditClient;
import com.stratio.app.microservices.weatherService.dto.WeatherEntityDto;
import com.stratio.app.microservices.weatherService.service.DasWeatherCallerService;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class DasWeatherCallerServiceImpl implements DasWeatherCallerService {


  private final WeatherAuditClient weatherAuditClient;

  @Override
  public CompletableFuture<List<WeatherEntityDto>> saveWeatherEntities(
      List<WeatherEntityDto> entities) {

    return CompletableFuture.supplyAsync(() -> retrieveSaveResponse(entities));
  }

  private List<WeatherEntityDto> retrieveSaveResponse(List<WeatherEntityDto> entities) {

//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Accept", "application/json");
//
//        HttpEntity<List<WeatherEntityDto>> request = new HttpEntity(entities,httpHeaders);
//
//        ResponseEntity<WeatherEntityDto[]> result = restTemplate.exchange(
//                protocol+weatherConfig.getDasWeatherServiceId()
//                        +':'+weatherConfig.getDasWeatherServicePort()
//                        +weatherConfig.getSaveResponse(),
//                HttpMethod.POST,
//                request,
//                WeatherEntityDto[].class);

    return weatherAuditClient.getSaveResponse(entities);
  }

  @Override
  public Optional<List<WeatherEntityDto>> getHistoric(String city, String text)
      throws HttpClientErrorException {

//    HttpHeaders httpHeaders = new HttpHeaders();
//    httpHeaders.add("Accept", "application/json");
//
//    HttpEntity<Object> request = new HttpEntity(null, httpHeaders);
//
//    String[] parameters = {city, text};
//
//    ResponseEntity<WeatherEntityDto[]> result = restTemplate.exchange(
//        protocol + weatherConfig.getDasWeatherServiceId()
//            + ':' + weatherConfig.getDasWeatherServicePort()
//            + weatherConfig.getGetHistoric(),
//        HttpMethod.GET,
//        request,
//        WeatherEntityDto[].class,
//        parameters);
    Optional<List<WeatherEntityDto>> ret = Optional
        .ofNullable(weatherAuditClient.gethistoric(city, text));

    return ret;
  }

  public DasWeatherCallerServiceImpl(WeatherAuditClient weatherAuditClient) {
    this.weatherAuditClient = weatherAuditClient;
  }
}
