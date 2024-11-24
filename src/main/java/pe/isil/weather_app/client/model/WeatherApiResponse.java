package pe.isil.weather_app.client.model;

public record WeatherApiResponse(
        Location location,
        Current current
) {
}
