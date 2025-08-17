import React, { useState, useEffect } from "react";
import {
  getWeather, getDetails, getForecast, getHistorical,
  getFavourites, addFavourite, removeFavourite, setAuth
} from "./api";
import SearchBar from "./components/SearchBar";
import Favourites from "./components/Favourites";
import WeatherInfo from "./components/WeatherInfo";
import Forecast from "./components/Forecast";
import Historical from "./components/Historical";
import LoginForm from "./components/LoginForm";  
import "./styles/App.css";

const DEFAULT_CITY = "Bengaluru";

function App() {
  const [city, setCity] = useState(DEFAULT_CITY);
  const [weather, setWeather] = useState(null);
  const [details, setDetails] = useState(null);
  const [forecast, setForecast] = useState(null);
  const [historical, setHistorical] = useState(null);
  const [favourites, setFavourites] = useState([]);
  const [lastAdded, setLastAdded] = useState(null);
  const [error, setError] = useState("");
  const [user, setUser] = useState(null);
  const [showLoginForm, setShowLoginForm] = useState(false);

  useEffect(() => {
    handleSearch(DEFAULT_CITY);
  }, []);

  useEffect(() => {
    if (user) {
      getFavourites()
        .then(setFavourites)
        .catch(() => setFavourites([]));
    } else {
      setFavourites([]);
    }
  }, [user]);

  function handleSearch(searchCity = null) {
    setError("");
    const queryCity = searchCity || city;
    setCity(queryCity);
    Promise.all([
      getWeather(queryCity),
      getDetails(queryCity),
      getForecast(queryCity),
      getHistorical(queryCity)
    ])
      .then(([w, d, f, h]) => {
        setWeather(w);
        setDetails(d);
        setForecast(f);
        setHistorical(h);
      })
      .catch(() => setError("City not found or server error."));
  }

  function handleAddFavourite() {
    if (!user) {
      setShowLoginForm(true);
      setError("You must sign in to add favourites.");
      return;
    }
    addFavourite(city)
      .then(() => {
        setFavourites(prev => [...prev, { city }]);
        setLastAdded(city);
        setTimeout(() => setLastAdded(null), 2500);
      })
      .catch(() => setError("Cannot add favourite (already exists or error)."));
  }

  function handleRemoveFavourite(ci) {
    removeFavourite(ci)
      .then(() => setFavourites(prev => prev.filter(f => f.city !== ci)))
      .catch(() => setError("Cannot remove favourite."));
  }

  function handleViewFavourite(cityName) {
  setCity(cityName);
  handleSearch(cityName);
}


  const doLogin = (username, password, setFormError) => {
    setAuth({ username, password });
    getFavourites().then(data => {
      setUser(username);
      setFavourites(data);
      setShowLoginForm(false);
      setError("");
    }).catch(() => {
      setFormError("Login failed. Check credentials.");
      setUser(null);
      setAuth({ username: null, password: null });
    });
  };

  const doLogout = () => {
    setUser(null);
    setAuth({ username: null, password: null });
    setFavourites([]);
  };

  return (
    <div className="app-container">
      <header className="app-header">
        <img src="/weather-logo.webp" alt="Weather Logo" className="app-logo"/>
        <h1>SkyCast: Your Weather Companion</h1>
        <div className="auth-buttons">
          {user ? (
            <div className="profile-section">
              <span>Welcome, <b>{user}</b></span>
              <button onClick={doLogout} className="logout-btn">Logout</button>
            </div>
          ) : (
            <button onClick={() => setShowLoginForm(true)} className="login-btn">Login</button>
          )}
        </div>
      </header>

      {showLoginForm && <LoginForm onLogin={doLogin} onGuest={() => setShowLoginForm(false)} />}

      <SearchBar city={city} setCity={setCity} onSearch={() => handleSearch(city)} onAddFavourite={handleAddFavourite} />
      {error && <div className="error-msg">{error}</div>}
      {user && (
        <Favourites
          favourites={favourites}
          onRemove={handleRemoveFavourite}
          lastAdded={lastAdded}
          onView={handleViewFavourite}
        />
      )}
      <WeatherInfo weather={weather} details={details} city={city} />
      <Forecast forecast={forecast} />
      <Historical historical={historical} />
    </div>
  );
}

export default App;
