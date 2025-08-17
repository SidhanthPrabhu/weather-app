package com.weather.management_system.controller;

import com.weather.management_system.model.CurrentWeatherResponse;
import com.weather.management_system.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "http://localhost:5173")
public class CurrentWeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public CurrentWeatherResponse getWeatherforCity(@RequestParam String city) {
        return weatherService.getWeather(city);
    }
}


