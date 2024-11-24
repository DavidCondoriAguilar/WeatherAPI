package pe.isil.weather_app.client.model;

import java.time.LocalDateTime;

public record Location(
        String name,
        String region,
        String country,
        String localtime
) {
}
