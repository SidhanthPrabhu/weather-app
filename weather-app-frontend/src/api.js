import axios from "axios";
const API_BASE = "http://localhost:8080/api/weather";

let basicAuth = null;

export function setAuth({ username, password }) {
  basicAuth = username && password ? { username, password } : null;
}

function withAuth(config={}) {
  return basicAuth ? { ...config, auth: basicAuth } : config;
}

export const getWeather = city =>
  axios.get(`${API_BASE}?city=${city}`).then(res => res.data);

export const getDetails = city =>
  axios.get(`${API_BASE}/details?city=${city}`).then(res => res.data);

export const getForecast = city =>
  axios.get(`${API_BASE}/forecast?city=${city}`).then(res => res.data);

export const getHistorical = city =>
  axios.get(`${API_BASE}/historical?city=${city}`).then(res => res.data);

export const getFavourites = () =>
  axios.get(`${API_BASE}/favourites`, withAuth()).then(res => res.data);

export const addFavourite = city =>
  axios.post(`${API_BASE}/favourites?city=${city}`, {}, withAuth());

export const removeFavourite = city =>
  axios.delete(`${API_BASE}/favourites/${city}`, withAuth());
