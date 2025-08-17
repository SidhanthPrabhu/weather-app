package com.weather.management_system.service;

import com.weather.management_system.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class WeatherService {

    @Value("${external.weather.api.key}")
    private String apiKeyAccuweather;

    private String ApiLocation = "https://dataservice.accuweather.com/locations/v1/cities/search?q=CITY&apikey=API_KEY";
    private String ApiCurrent = "https://dataservice.accuweather.com/currentconditions/v1/LOCATION_KEY?apikey=API_KEY";

    @Autowired
    private RestTemplate restTemplate;

    public LocationKeyResponse getLocationKey(String city){
        String finalApi = ApiLocation.replace("CITY",city).replace("API_KEY",apiKeyAccuweather);
        try {
            // Change response type to array
            ResponseEntity<LocationKeyResponse[]> response = restTemplate.exchange(
                    finalApi,
                    HttpMethod.GET,
                    null,
                    LocationKeyResponse[].class
            );

            LocationKeyResponse[] locations = response.getBody();
            if (locations == null || locations.length == 0) {
                throw new RuntimeException("No location found for city: " + city);
            }

            // Return first result (most relevant match)
            return locations[0];
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to get location key for city: " + city, e);
        }
    }

    public CurrentWeatherResponse getWeather(String city) {
        LocationKeyResponse locationResponse = getLocationKey(city);
        String locationKey = locationResponse.getKey();
        String finalApi = ApiCurrent.replace("LOCATION_KEY", locationKey)
                .replace("API_KEY", apiKeyAccuweather);

        try {
            CurrentWeatherResponse.WeatherData[] weatherArray = restTemplate.getForObject(
                    finalApi,
                    CurrentWeatherResponse.WeatherData[].class
            );

            if (weatherArray == null || weatherArray.length == 0) {
                throw new RuntimeException("No weather data found for city: " + city);
            }

            CurrentWeatherResponse response = new CurrentWeatherResponse();
            response.setData(Arrays.asList(weatherArray));
            return response;
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to get weather data for city: " + city, e);
        }
    }

    private String ApiForecast = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/LOCATION_KEY?apikey=API_KEY&metric=true";

    public ForecastResponse getForecast(String city) {
        try {

            LocationKeyResponse locationResponse = getLocationKey(city);
            String locationKey = locationResponse.getKey();

            if (locationKey == null || locationKey.isEmpty()) {
                throw new RuntimeException("Invalid location key for city: " + city);
            }

            String finalApi = ApiForecast.replace("LOCATION_KEY", locationKey)
                    .replace("API_KEY", apiKeyAccuweather);


            ResponseEntity<ForecastResponse> response = restTemplate.exchange(
                    finalApi,
                    HttpMethod.GET,
                    null,
                    ForecastResponse.class
            );

            ForecastResponse forecast = response.getBody();
            if (forecast == null || forecast.getDailyForecasts() == null) {
                throw new RuntimeException("Received empty forecast data");
            }

            return forecast;
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to get forecast. Please try again later.");
        }
    }

    private String ApiHistorical = "http://dataservice.accuweather.com/currentconditions/v1/LOCATION_KEY/historical/24?apikey=API_KEY&details=true";

    public HistoricalWeatherResponse getHistoricalWeather(String city) {
        try {
            LocationKeyResponse locationResponse = getLocationKey(city);
            String locationKey = locationResponse.getKey();
            String finalApi = ApiHistorical.replace("LOCATION_KEY", locationKey)
                    .replace("API_KEY", apiKeyAccuweather);

            ResponseEntity<HistoricalWeatherResponse.HistoricalData[]> response = restTemplate.exchange(
                    finalApi,
                    HttpMethod.GET,
                    null,
                    HistoricalWeatherResponse.HistoricalData[].class
            );

            HistoricalWeatherResponse result = new HistoricalWeatherResponse();
            result.setHistoricalData(Arrays.asList(response.getBody()));
            return result;
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to get historical weather", e);
        }
    }

    private String apiKeyWeatherStack = "aaa196fe7c41d66ee9a122cfe06b425a";
    private String ApiWeatherDetails = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    public WeatherDetailsResponse getWeatherDetails(String city) {
        try {
            String finalApi = ApiWeatherDetails.replace("CITY", city)
                    .replace("API_KEY", apiKeyWeatherStack);

            ResponseEntity<WeatherDetailsResponse> response = restTemplate.exchange(
                    finalApi,
                    HttpMethod.GET,
                    null,
                    WeatherDetailsResponse.class
            );

            WeatherDetailsResponse details = response.getBody();
            if (details == null || details.getCurrent() == null) {
                throw new RuntimeException("Empty weather details response");
            }
            return details;
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to get weather details", e);
        }
    }
}
