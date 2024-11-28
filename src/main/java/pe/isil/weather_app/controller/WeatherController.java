package pe.isil.weather_app.service;

import org.springframework.stereotype.Service;
import pe.isil.weather_app.client.WeatherApiClient;
import pe.isil.weather_app.client.model.WeatherApiResponse;
import pe.isil.weather_app.model.WeatherData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class weatherservice {

    private final WeatherApiClient weatherApiClient;

    public weatherservice(WeatherApiClient weatherApiClient){
        this.weatherApiClient = weatherApiClient;
    }

    // Obtener el clima actual para varias ciudades
    public List<WeatherData> getCurrentWeather(){
        var listOfCities = List.of("Lima", "Buenos Aires", "Vancouver", "Madrid");
        var listData = new ArrayList<WeatherData>();

        listOfCities.forEach(city -> {
            var dataResponse = weatherApiClient.getWeather(city);
            if(dataResponse.isEmpty()){
                throw new RuntimeException("No se encontró información para la ciudad "+ city);
            }
            var data = dataResponse.get();
            listData.add(mapTo(data));
        });

        return listData;
    }

    // Obtener el clima para una ciudad específica
    public WeatherData getWeatherForCity(String city) {
        Optional<WeatherApiResponse> dataResponse = weatherApiClient.getWeather(city);

        if (dataResponse.isEmpty()) {
            throw new RuntimeException("No se encontró información para la ciudad " + city);
        }

        return mapTo(dataResponse.get());
    }

    // Método privado para mapear la respuesta de la API a un objeto WeatherData
    private WeatherData mapTo(WeatherApiResponse data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return new WeatherData(
                data.location().name(),
                data.location().region(),
                data.location().country(),
                data.current().tempC(),
                data.current().tempF(),
                data.current().condition().text(),
                LocalDateTime.parse(data.location().localtime(), formatter));
    }
}
