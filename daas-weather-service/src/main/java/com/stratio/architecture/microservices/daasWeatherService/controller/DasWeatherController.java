package com.stratio.architecture.microservices.daasWeatherService.controller;

import com.stratio.architecture.microservices.daasWeatherService.dto.WeatherEntityDto;
import com.stratio.architecture.microservices.daasWeatherService.service.DasWeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(value = "EOS Weather DAS Service", produces = MediaType.APPLICATION_JSON_VALUE)
public class DasWeatherController {

  private final DasWeatherService service;
  private static final Logger log = LoggerFactory.getLogger(DasWeatherController.class);


  @PostMapping("/weatherAudit")
  @ApiOperation("Save a forecast of a City in audit table")
  public ResponseEntity<List<WeatherEntityDto>> saveEntity(@ApiParam("request body")
  @RequestBody List<WeatherEntityDto> weatherEntityDtos)
      throws Exception {

    log.info("Entrada saveEntity");
    List<WeatherEntityDto> result = service.saveWeatherAudit(weatherEntityDtos);
    return ResponseEntity.created(new URI("/api/v1/wheatherAudit"))
        .body(result);
  }

  @GetMapping("/weatherAudit/city({city}/prediction/{prediction}")
  @ApiOperation("Get forecast from audit table filter by city and type of prediction (sunny, storm,..)")
  public ResponseEntity <List<WeatherEntityDto>> gethistoric(@PathVariable String city,
      @PathVariable String prediction)
      throws Exception{

    log.info("Entrada getHistoric");
    List<WeatherEntityDto> result = service.getHistoricByCityPrediction(city,prediction);

    return ResponseEntity.ok(result);
  }

  public DasWeatherController(DasWeatherService service) {
    this.service = service;
  }
}
