import React from "react";
import { formatDateTime, getAccuWeatherIcon } from "./../utils";
import "./../styles/WeatherInfo.css";

function WeatherInfo({ weather, details, city }) {
  if (!weather && !details) return null;
  const wd = weather?.data?.[0];

  return (
    <div className="weather-info">
      <h3>Current Weather {city ? `(${city})` : ""}</h3>
      {wd && (
        <div className="current-weather-block">
          <div className="weather-main">
            <strong style={{ color: "#2563eb", fontSize: "1.17rem" }}>
              {wd.WeatherText}
            </strong>
            {typeof wd.WeatherIcon === "number" &&
              <img
                src={getAccuWeatherIcon(wd.WeatherIcon)}
                alt={wd.WeatherText}
                style={{ verticalAlign: "middle", marginLeft: 18, width: 42, height: 42 }}
                onError={e => { e.target.style.display = "none"; }}
              />
            }
          </div>
          <div className="weather-details">
            <b>Temperature: </b>
              {wd.Temperature?.Metric?.value}°{wd.Temperature?.Metric?.unit}
            <span style={{ marginLeft: 14 }}>
              {wd.IsDayTime ? "☀️ Daytime" : "🌙 Nighttime"}
            </span>
            <div style={{ fontSize: "0.95rem", marginTop: 6 }}>Observed at: {formatDateTime(wd.LocalObservationDateTime)}</div>
          </div>
        </div>
      )}
      {details?.current && (
        <div className="weather-subdetails">
          <h4>Details</h4>
          <table className="weather-table">
            <tbody>
              <tr><td>Observation Time</td><td>{details.current.observation_time}</td></tr>
              <tr><td>Wind</td><td>{details.current.wind_speed} km/h {details.current.wind_dir}</td></tr>
              <tr><td>Pressure</td><td>{details.current.pressure} hPa</td></tr>
              <tr><td>Humidity</td><td>{details.current.humidity}%</td></tr>
              <tr><td>Feels Like</td><td>{details.current.feelslike}°C</td></tr>
              <tr><td>UV Index</td><td>{details.current.uv_index}</td></tr>
              <tr><td>Air Quality</td>
                <td>
                  CO={details.current.air_quality?.co},&nbsp;
                  NO₂={details.current.air_quality?.no2},&nbsp;
                  O₃={details.current.air_quality?.o3}
                </td>
              </tr>
              <tr>
                <td>Weather Descriptions</td>
                <td>{details.current.weather_descriptions && details.current.weather_descriptions.join(", ")}</td>
              </tr>
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
export default WeatherInfo;
