package pe.isil.weather_app.service;

import org.springframework.stereotype.Service;
import pe.isil.weather_app.client.WeatherApiClient;
import pe.isil.weather_app.client.model.WeatherApiResponse;
import pe.isil.weather_app.model.WeatherData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private final WeatherApiClient weatherApiClient;

    public WeatherService (WeatherApiClient weatherApiClient){
        this.weatherApiClient = weatherApiClient;
    }

    public List<WeatherData> getCurrentWeather(){
        var listOfCities = List.of("Lima", "Buenos Aires", "Vancouver", "Madrid");
        var listData = new ArrayList<WeatherData>();


        listOfCities.forEach(
                city -> {
                    var dataResponse = weatherApiClient.getWeather(city);
                    if(dataResponse.isEmpty()){
                        throw new RuntimeException("No se encontró informacion para la ciudad "+ city);
                    }
                    var data = dataResponse.get();
                    listData.add(mapTo(data));
                }
        );

        return listData;
    }

    private WeatherData mapTo (WeatherApiResponse data){
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
