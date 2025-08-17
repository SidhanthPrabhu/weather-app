package com.weather.management_system.controller;

import com.weather.management_system.model.HistoricalWeatherResponse;
import com.weather.management_system.service.WeatherService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather/historical")
@CrossOrigin(origins = "http://localhost:5173")
public class HistoricalWeatherController {
    @Autowired
    private WeatherService weatherService;



    @GetMapping
    public HistoricalWeatherResponse getHistorical(@RequestParam String city){
        return weatherService.getHistoricalWeather(city);
    }
}
