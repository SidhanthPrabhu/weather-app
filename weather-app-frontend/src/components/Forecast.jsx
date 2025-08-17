import React from "react";
import { formatDateTime, getAccuWeatherIcon } from "./../utils";
import "./../styles/Forecast.css";

function Forecast({ forecast }) {
  if (!forecast?.DailyForecasts) return null;
  return (
    <div className="forecast">
      <h3>Forecast</h3>
      <ul>
        {forecast.DailyForecasts.map((f, i) => (
          <li key={f.Date || f.epochDate} className="forecast-row">
            <div>
              <b>{formatDateTime(f.Date)}</b>
              <br />
              <img src={getAccuWeatherIcon(f.Day.Icon)}  width={32} height={32} style={{verticalAlign: "middle"}} />
              <span>{f.Day.IconPhrase}</span>
              {" | "}
              <img src={getAccuWeatherIcon(f.Night.Icon)}  width={32} height={32} style={{verticalAlign: "middle"}} />
              <span>{f.Night.IconPhrase}</span>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}
export default Forecast;
