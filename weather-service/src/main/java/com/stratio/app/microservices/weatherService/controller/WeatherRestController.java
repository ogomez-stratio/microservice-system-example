package com.stratio.app.microservices.weatherService.controller;

import com.stratio.app.microservices.weatherService.dto.WeatherEntityDto;
import com.stratio.app.microservices.weatherService.dto.WeatherResponseDto;
import com.stratio.app.microservices.weatherService.service.DasWeatherCallerService;
import com.stratio.app.microservices.weatherService.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/v1")
@Api(value = "EOS Weather Service", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class WeatherRestController {

  private final WeatherService weatherService;
  private final DasWeatherCallerService dasWeatherCallerService;


  @GetMapping("weather/{city}")
  @ApiOperation(value = "Given a city Get weather prediction for next week")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Standard response for successful HTTP requests"),
      @ApiResponse(code = 404, message = "User does not exist or is missing city")
  })
  public ResponseEntity<WeatherResponseDto> getWeatherByCity(
      @ApiParam(required = true, value = "City we want to request weather prediction")
      @PathVariable String city) throws HttpClientErrorException, UnsupportedEncodingException {

    log.info("Entrada getWeatherByCity");
    Optional<WeatherResponseDto> response = weatherService.getweatherBycity(city);
    return response.map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
  }

  @GetMapping("weather/history/city/{city}/prediction/{prediction}")
  @ApiOperation(value = "Given a city and the text of a prediction get weather prediction stored in audit database")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Standard response for successful HTTP requests"),
      @ApiResponse(code = 404, message = "User does not exist or is missing city")
  })
  public ResponseEntity<List<WeatherEntityDto>> getHistory(
      @ApiParam(required = true, value = "City we want to request historic weather prediction")
      @PathVariable String city,
      @ApiParam(required = true, value = "Type of prediction we want to filter (sunny,cloudy,rain,..) ")
      @PathVariable String prediction
  ) throws HttpClientErrorException {

    log.info("Entrada getHistory");
    Optional<List<WeatherEntityDto>> response = dasWeatherCallerService
        .getHistoric(city, prediction);
    return response.map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
  }

  public WeatherRestController(WeatherService weatherService, DasWeatherCallerService dasWeatherCallerService) {
    this.weatherService = weatherService;
    this.dasWeatherCallerService = dasWeatherCallerService;
  }
}
