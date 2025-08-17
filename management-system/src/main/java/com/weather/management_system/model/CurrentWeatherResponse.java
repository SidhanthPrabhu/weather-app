package com.weather.management_system.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CurrentWeatherResponse {
    private List<WeatherData> data = new ArrayList<>();  // Initialized list and proper field name

    @Getter
    @Setter
    public static class WeatherData {
        @JsonProperty("LocalObservationDateTime")
        private Date localObservationDateTime;
        @JsonProperty("EpochTime")
        private int epochTime;
        @JsonProperty("WeatherText")
        private String weatherText;
        @JsonProperty("WeatherIcon")
        private int weatherIcon;
        @JsonProperty("HasPrecipitation")
        private boolean hasPrecipitation;
        @JsonProperty("PrecipitationType")
        private Object precipitationType;
        @JsonProperty("IsDayTime")
        private boolean isDayTime;
        @JsonProperty("Temperature")
        private Temperature temperature;

        @Getter
        @Setter
        public static class Temperature {
            @JsonProperty("Metric")
            private Metric metric;
            @JsonProperty("Imperial")
            private Imperial imperial;

            @Getter
            @Setter
            public static class Metric {
                @JsonProperty("Value")
                private double value;
                @JsonProperty("Unit")
                private String unit;
                @JsonProperty("UnitType")
                private int unitType;
            }

            @Getter
            @Setter
            public static class Imperial {
                @JsonProperty("Value")
                private double value;
                @JsonProperty("Unit")
                private String unit;
                @JsonProperty("UnitType")
                private int unitType;
            }
        }
    }
}


