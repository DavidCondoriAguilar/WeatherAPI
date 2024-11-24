package pe.isil.weather_app.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pe.isil.weather_app.client.model.WeatherApiResponse;

import java.util.Optional;

@Component
public class WeatherApiClient {

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "[URL DE WEATHER API, NO OLVIDAR COLOCAR 's%' EN EL PARAMETRO DE CIUDAD]";

    public WeatherApiClient (RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Optional<WeatherApiResponse> getWeather(String city){
        String url = String.format(BASE_URL, city);
        WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);
        return Optional.ofNullable(response);
    }

}
