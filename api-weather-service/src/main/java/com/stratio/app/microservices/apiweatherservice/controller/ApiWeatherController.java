package com.stratio.app.microservices.apiweatherservice.controller;

import com.stratio.app.microservices.apiweatherservice.dto.WeatherEntityDto;
import com.stratio.app.microservices.apiweatherservice.dto.WeatherResponseDto;
import com.stratio.app.microservices.apiweatherservice.service.ApiCallerService;
import com.stratio.architecture.microservices.logging.marker.LoggingMarkers;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@Slf4j
public class ApiWeatherController {

  private final ApiCallerService apiCallerService;

  @GetMapping("weather/{city}")
  @ApiOperation(value = "Given a city Get weather prediction for next week")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Standard response for successful HTTP requests"),
      @ApiResponse(code = 404, message = "User does not exist or is missing city")
  })
  public ResponseEntity<WeatherResponseDto> getWeatherByCity(
      @ApiParam(required = true, value = "City we want the weather prediction",
          allowableValues = "madrid, barcelona, bilbao, seville" )
      @PathVariable String city) throws HttpClientErrorException {

    log.info("Entrada getWeatherByCity");
    log.debug("log a debug");
    log.info(LoggingMarkers.AUDIT_MARKER, "pepe");

    Optional<WeatherResponseDto> ret = Optional.ofNullable(apiCallerService.getPrediction(city));

    return ret.map(ResponseEntity::ok)
        .orElse(ResponseEntity.noContent().build());
  }

  @GetMapping("weather/history/city/{city}/prediction/{prediction}")
  @ApiOperation(value = "Given a city and the text of a prediction get weather prediction stored in audit database")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Standard response for successful HTTP requests"),
      @ApiResponse(code = 404, message = "User does not exist or is missing city")
  })
  public ResponseEntity<List<WeatherEntityDto>> getPredictionHistory(
      @ApiParam(required = true, value = "City we want to request historic weather prediction")
      @PathVariable String city,
      @ApiParam(required = true, value = "Type of prediction we want to filter (sunny,cloudy,rain,..) ")
      @PathVariable String prediction
  ) throws HttpClientErrorException {

    log.info("Entrada getPredictionHistory");
    Optional<List<WeatherEntityDto>> ret = Optional
        .ofNullable(apiCallerService.getPredictionHistory(city, prediction));
    return ret.map(ResponseEntity::ok)
        .orElse(ResponseEntity.noContent().build());
  }
  public ApiWeatherController(ApiCallerService apiCallerService) {
    this.apiCallerService = apiCallerService;
  }
}
