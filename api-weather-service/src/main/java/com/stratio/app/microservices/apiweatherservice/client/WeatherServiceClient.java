package com.stratio.app.microservices.apiweatherservice.client;

import com.stratio.app.microservices.apiweatherservice.dto.WeatherEntityDto;
import com.stratio.app.microservices.apiweatherservice.dto.WeatherResponseDto;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "weather-client", url = "${dependencies.weatherServiceBasePath}")
public interface WeatherServiceClient {

  @RequestMapping("/v1/weather/{city}")
  WeatherResponseDto getPrediction(@PathVariable("city") String city);

  @RequestMapping("/v1/weather/history/city/{city}/prediction/{prediction}")
  List<WeatherEntityDto> getPredictionHistory(@PathVariable("city") String city,
      @PathVariable("prediction") String prediction);
}
