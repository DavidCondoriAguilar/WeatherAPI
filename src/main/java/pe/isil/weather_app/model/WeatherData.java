package pe.isil.weather_app.model;

import java.time.LocalDateTime;

public record WeatherData(
        String city,
        String region,
        String country,
        double tempC,
        double tempF,
        String condition,
        LocalDateTime dateTime
) {
}
