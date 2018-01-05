package com.stratio.architecture.microservices.daasWeatherService.dao;

import com.stratio.architecture.microservices.daasWeatherService.domain.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherRepository extends JpaRepository<WeatherEntity,Long>{

    List<WeatherEntity> findByDesCityLikeIgnoreCaseAndDesTextLikeIgnoreCase(String city, String text);
}
