package com.stratio.app.microservices.weatherService.client;

import com.stratio.app.microservices.weatherService.dto.WeatherEntityDto;
import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "audit-client", url = "${service.auditServiceBasePath}")
public interface WeatherAuditClient {

  @RequestMapping("${service.saveResponse}")
  List<WeatherEntityDto> getSaveResponse(@PathVariable("city") List<WeatherEntityDto> entities);

  @RequestMapping("${service.getHistoric}")
  List<WeatherEntityDto> gethistoric(@PathVariable("city") String city,
      @PathVariable("prediction") String prediction);
}

