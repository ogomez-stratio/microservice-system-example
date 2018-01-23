package com.stratio.app.microservices.weatherService.service.impl;

import com.stratio.app.microservices.weatherService.client.WeatherAuditClient;
import com.stratio.app.microservices.weatherService.client.YahooClient;
import com.stratio.app.microservices.weatherService.config.WeatherConfig;
import com.stratio.app.microservices.weatherService.dto.CitiesEnum;
import com.stratio.app.microservices.weatherService.dto.WeatherEntityDto;
import com.stratio.app.microservices.weatherService.dto.WeatherResponseDto;
import com.stratio.app.microservices.weatherService.service.WeatherService;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class WeatherServiceImpl implements WeatherService {


  private final DasWeatherCallerServiceImpl callerService;

  private CitiesEnum cities;

  private final WeatherAuditClient weatherAuditClient;
  private final YahooClient yahooClient;
  private final WeatherConfig config;

  @Value("${service.format}")
  private String format;

  public WeatherServiceImpl(
      DasWeatherCallerServiceImpl callerService,
      WeatherAuditClient weatherAuditClient,
      YahooClient yahooClient,
      WeatherConfig config) {
    this.callerService = callerService;
    this.weatherAuditClient = weatherAuditClient;
    this.yahooClient = yahooClient;
    this.config = config;
  }

  @Override
  public Optional<WeatherResponseDto> getweatherBycity(String city)
      throws HttpClientErrorException, UnsupportedEncodingException {

    Optional<WeatherResponseDto> ret = Optional.empty();
    List<WeatherEntityDto> entityDto = new ArrayList<>();

    if (EnumUtils.isValidEnum(CitiesEnum.class, city)) {

//           String uriString = config.getBaseUrl()+config.getPath()+"?q="
//                   +config.getQuery()+CitiesEnum.valueOf(city).getCode()+"&format="+config.getFormat();

//            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uriString);
//
//            ret = restTemplate.getForObject(builder.build(true).toUri(), WeatherResponseDto.class);

      String q = config.getQuery() + CitiesEnum.valueOf(city).getCode();
      ret = Optional.ofNullable(yahooClient.getweatherByCity(q, format));
      if (ret.isPresent()) {
        String cityValue = ret.get().getQuery().getResults().getChannel().getLocation().getCity();
        ret.get().getQuery().getResults().getChannel().getItem().getForecast().forEach(
            f -> entityDto.add(WeatherEntityDto.builder()
                .desCity(cityValue)
                .desDate(f.getDate())
                .desDay(f.getDay())
                .desHigh(f.getHigh())
                .desLow(f.getLow())
                .desText(f.getText())
                .build())
        );

        callerService.saveWeatherEntities(entityDto);
      }
    }

    return ret;
  }
}
