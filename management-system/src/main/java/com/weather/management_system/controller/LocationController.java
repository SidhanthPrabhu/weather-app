package com.weather.management_system.controller;

import com.weather.management_system.model.LocationKeyResponse;
import com.weather.management_system.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather/location")
@CrossOrigin(origins = "http://localhost:5173")
public class LocationController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public LocationKeyResponse getLocationKey(@RequestParam String city){
        return weatherService.getLocationKey(city);

    }
}
