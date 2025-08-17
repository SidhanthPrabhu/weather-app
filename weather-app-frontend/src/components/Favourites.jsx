import React from "react";
import "./../styles/Favourites.css";

function Favourites({ favourites, onRemove, onView, lastAdded }) {
  return (
    <div className="favourites">
      <h3>Favourite Locations</h3>
      {lastAdded && <div className="fav-success">{lastAdded} added to favourites!</div>}
      <ul>
        {favourites.map(fav => (
          <li key={fav.city} className="fav-row">
            <span className="fav-city">{fav.city}</span>
            <button className="fav-view-btn" onClick={() => onView(fav.city)}>
              View
            </button>
            <button className="fav-remove-btn" onClick={() => onRemove(fav.city)}>
              Remove
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}
export default Favourites;
