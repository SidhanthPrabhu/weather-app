package com.weather.management_system.controller;

import com.weather.management_system.model.WeatherDetailsResponse;
import com.weather.management_system.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather/details")
@CrossOrigin(origins = "http://localhost:5173")
public class WeatherDetailsController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public WeatherDetailsResponse getWeatherDetails(@RequestParam String city){
        return weatherService.getWeatherDetails(city);
    }
}
