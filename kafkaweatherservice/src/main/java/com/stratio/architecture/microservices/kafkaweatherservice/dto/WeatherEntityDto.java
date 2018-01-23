package com.stratio.architecture.microservices.kafkaweatherservice.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherEntityDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long auditId;

    private String desCity;

    private String desDate;

    private String desDay;

    private String desHigh;

    private String desLow;

    private String desText;
}
