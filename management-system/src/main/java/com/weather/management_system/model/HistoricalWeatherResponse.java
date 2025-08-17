package com.weather.management_system.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HistoricalWeatherResponse {
    @JsonProperty("data")
    private List<HistoricalData> historicalData;

    @Getter
    @Setter
    public static class HistoricalData {
        @JsonProperty("LocalObservationDateTime")
        private String localObservationDateTime;
        @JsonProperty("EpochTime")
        private long epochTime;
        @JsonProperty("WeatherText")
        private String weatherText;
        @JsonProperty("WeatherIcon")
        private int weatherIcon;
        @JsonProperty("HasPrecipitation")
        private boolean hasPrecipitation;
        @JsonProperty("PrecipitationType")
        private String precipitationType;
        @JsonProperty("IsDayTime")
        private boolean isDayTime;

        @JsonProperty("Temperature")
        private Temperature temperature;

        @JsonProperty("RealFeelTemperature")
        private RealFeelTemperature realFeelTemperature;
    }

    @Getter
    @Setter
    public static class Temperature {
        @JsonProperty("Metric")
        private Metric metric;
        @JsonProperty("Imperial")
        private Imperial imperial;
    }

    @Getter
    @Setter
    public static class RealFeelTemperature {
        @JsonProperty("Metric")
        private Metric metric;
        @JsonProperty("Imperial")
        private Imperial imperial;
    }

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