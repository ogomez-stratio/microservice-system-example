package com.stratio.app.microservices.weatherService.client;

import com.stratio.app.microservices.weatherService.dto.WeatherResponseDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "yahoo-client", url = "${service.baseUrl}")
public interface YahooClient {

  @RequestMapping("${service.path}")
  WeatherResponseDto getweatherByCity(@RequestParam(value = "q") String query,
      @RequestParam("format") String format);
}
