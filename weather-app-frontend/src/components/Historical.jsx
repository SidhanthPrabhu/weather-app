import React from "react";
import { formatDateTime, getAccuWeatherIcon } from "./../utils";
import "./../styles/Historical.css";

function Historical({ historical }) {
  if (!historical?.data) return null;
  return (
    <div className="historical">
      <h3>Historical (Past 24h)</h3>
      <ul>
        {historical.data.map((h, idx) => (
          <li key={idx} className="historical-row">
            <b>{formatDateTime(h.LocalObservationDateTime)}:</b>
            <img src={getAccuWeatherIcon(h.WeatherIcon)} width={32} height={32} style={{ verticalAlign: "middle", marginRight: 4 }} />
            <span>{h.WeatherText}
            </span>
          </li>
        ))}
      </ul>
    </div>
  );
}
export default Historical;
