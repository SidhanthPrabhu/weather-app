export function formatDateTime(dateString) {
  if (!dateString) return "";
  const dt = new Date(dateString);
  return dt.toLocaleString("en-IN", {
    day: "2-digit", month: "short", year: "numeric",
    hour: "2-digit", minute: "2-digit", hour12: true
  });
}

export function getAccuWeatherIcon(iconNumber) {
  if (iconNumber == null) return null;
  let numStr = iconNumber.toString().padStart(2, "0");
  return `https://developer.accuweather.com/sites/default/files/${numStr}-s.png`;
}
