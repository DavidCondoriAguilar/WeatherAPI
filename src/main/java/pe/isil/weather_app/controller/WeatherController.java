package pe.isil.weather_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.isil.weather_app.service.WeatherService;

@Controller
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        var serviceResult = service.getCurrentWeather(); // Asegúrate de que esta llamada retorne datos
        model.addAttribute("weatherData", serviceResult); // Debe contener datos
        return "index";  // Retorna la vista que corresponde
    }


    // Método POST para manejar el formulario de búsqueda de ciudad
    @PostMapping("/search")
    public String search(@RequestParam("city") String city, Model model) {
        // Procesa la ciudad recibida desde el formulario
        var searchResult = service.getWeatherForCity(city);  // Llama al servicio para obtener el clima de la ciudad
        model.addAttribute("weatherData", searchResult);  // Pasa los datos al modelo para la vista
        return "index";  // Redirige a la misma vista "index.html" con los nuevos datos
    }
}
