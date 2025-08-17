import React, { useState } from "react";
import "./../styles/App.css";

export default function LoginForm({ onLogin, onGuest }) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = e => {
    e.preventDefault();
    if (!username || !password) {
      setError("Username and password required.");
      return;
    }
    onLogin(username, password, setError);
  };

  return (
    <div className="login-overlay">
      <div className="login-form-modal">
        <h2>Sign In</h2>
        <form onSubmit={handleLogin}>
          <input type="text" placeholder="Username" value={username}
            onChange={e => setUsername(e.target.value)} />
          <input type="password" placeholder="Password" value={password}
            onChange={e => setPassword(e.target.value)} />
          <button type="submit">Sign In</button>
        </form>
        {error && <div className="error-msg">{error}</div>}
        <button className="guest-btn" onClick={onGuest}>Continue as Guest</button>
      </div>
    </div>
  );
}
