package com.weather.management_system.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LocationKeyResponse {

    @JsonProperty("Key")
    private String key;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("EnglishName")
    private String englishName;
    private Region region;
    private Country country;
    private TimeZone timeZone;
    private GeoPosition geoPosition;

    public class Country{
        @JsonProperty("ID")
        private String iD;
        @JsonProperty("LocalizedName")
        private String localizedName;
        @JsonProperty("EnglishName")
        private String englishName;
    }

    public class GeoPosition{
        @JsonProperty("Latitude")
        private double latitude;
        @JsonProperty("Longitude")
        private double longitude;
    }

    public class Region{
        @JsonProperty("ID")
        private String iD;
        @JsonProperty("LocalizedName")
        private String localizedName;
        @JsonProperty("EnglishName")
        private String englishName;
    }

    public class TimeZone{
        @JsonProperty("Code")
        private String code;
        @JsonProperty("Name")
        private String name;
        @JsonProperty("GmtOffset")
        private double gmtOffset;
        @JsonProperty("IsDaylightSaving")
        private boolean isDaylightSaving;
        @JsonProperty("NextOffsetChange")
        private Object nextOffsetChange;
    }
}

@Getter
@Setter
class LocationKeyResponseWrapper {
    private List<LocationKeyResponse> locations;
}
