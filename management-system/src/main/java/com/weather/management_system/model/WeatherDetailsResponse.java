package com.weather.management_system.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDetailsResponse {
    @JsonProperty("current")
    private Current current;

    @Getter
    @Setter
    public static class Current {
        @JsonProperty("observation_time")
        private String observationTime;
        @JsonProperty("temperature")
        private int temperature;
        @JsonProperty("weather_code")
        private int weatherCode;
        @JsonProperty("wind_speed")
        private int windSpeed;
        @JsonProperty("wind_degree")
        private int windDegree;
        @JsonProperty("wind_dir")
        private String windDirection;
        @JsonProperty("pressure")
        private int pressure;
        @JsonProperty("precip")
        private int precipitation;
        @JsonProperty("humidity")
        private int humidity;
        @JsonProperty("cloudcover")
        private int cloudCover;
        @JsonProperty("feelslike")
        private int feelsLike;
        @JsonProperty("uv_index")
        private int uvIndex;
        @JsonProperty("visibility")
        private int visibility;
        @JsonProperty("is_day")
        private String isDay;

        @JsonProperty("air_quality")
        private AirQuality airQuality;

        @JsonProperty("weather_descriptions")
        private String[] weatherDescriptions;
    }

    @Getter
    @Setter
    public static class AirQuality {
        @JsonProperty("co")
        private double carbonMonoxide;
        @JsonProperty("no2")
        private double nitrogenDioxide;
        @JsonProperty("o3")
        private double ozone;
        @JsonProperty("so2")
        private double sulphurDioxide;
        @JsonProperty("pm2_5")
        private double pm25;
        @JsonProperty("pm10")
        private double pm10;
        @JsonProperty("us-epa-index")
        private int usEpaIndex;
        @JsonProperty("gb-defra-index")
        private int gbDefraIndex;
    }
}
