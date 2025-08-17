import React from "react";
import "./../styles/App.css";

function SearchBar({ city, setCity, onSearch, onAddFavourite }) {
  return (
    <div className="search-bar">
      <input
        value={city}
        onChange={e => setCity(e.target.value)}
        placeholder="Enter city name"
        className="search-input"
        autoFocus
      />
      <button onClick={onSearch} className="main-btn">Search</button>
      <button onClick={onAddFavourite} className="sec-btn" disabled={!city}>Add to Favourites</button>
    </div>
  );
}
export default SearchBar;
