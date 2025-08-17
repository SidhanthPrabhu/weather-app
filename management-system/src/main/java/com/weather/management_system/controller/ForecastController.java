package com.weather.management_system.controller;

import com.weather.management_system.model.ForecastResponse;
import com.weather.management_system.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather/forecast")
@CrossOrigin(origins = "http://localhost:5173")
public class ForecastController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ForecastResponse getForecastWeather(@RequestParam String city) {
        return weatherService.getForecast(city);
    }
}
