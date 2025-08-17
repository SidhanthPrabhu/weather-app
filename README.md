 # SkyCast - Weather Management App

A simple, responsive web app that provides real-time weather, forecasts, and historical data for cities worldwide. Built with React (Vite) frontend and Spring Boot backend using AccuWeather API.

## Features
- Search current weather by city
- Detailed weather info & air quality
- 5-day forecast with icons
- Historical weather data (past 24h)
- User authentication with favourites management
- Responsive design for all devices

## Tech Stack
- Frontend: React, Vite, Axios, CSS
- Backend: Spring Boot, Spring Security, H2 Database
- Weather API: AccuWeather

## Setup
1. Add your AccuWeather API key in backend's `application.properties`.
2. Run backend:
   ./mvnw spring-boot:run
3. Run frontend:
npm install
npm install axios
npm run dev
4. Access app at `http://localhost:5173`

## Credentials
- Username: `user`
- Password: `password`

## Notes
- Free API tier limits requests; be mindful of usage.
- Currently uses Basic Auth;
---

Feel free to reach out for improvements or help!
