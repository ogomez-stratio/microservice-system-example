package com.stratio.app.microservices.weatherService.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum  CitiesEnum {

    madrid("766273"),
    barcelona("753692"),
    bilbao("754542"),
    seville("774508");

    private String code;
}
