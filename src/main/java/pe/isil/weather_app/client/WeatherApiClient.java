package pe.isil.weather_app.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pe.isil.weather_app.client.model.WeatherApiResponse;

import java.util.Optional;

@Component
public class WeatherApiClient {

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "http://api.weatherapi.com/v1/current.json?key=%s&q=%s&aqi=no";
    private final String apiKey = "62e734796c7d47309b330506242811";  // Tu clave API de WeatherAPI

    public WeatherApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<WeatherApiResponse> getWeather(String city) {
        try {
            // Usando String.format para construir dinámicamente la URL
            String url = String.format(BASE_URL, apiKey, city);
            WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);
            return Optional.ofNullable(response);
        } catch (Exception e) {
            // Manejo de errores: loguear el problema y devolver Optional.empty()
            System.err.println("Error al obtener el clima: " + e.getMessage());
            return Optional.empty();
        }
    }
}
