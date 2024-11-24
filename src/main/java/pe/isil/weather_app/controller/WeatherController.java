package pe.isil.weather_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pe.isil.weather_app.service.WeatherService;

@Controller
public class WeatherController {

    private WeatherService service;

    public WeatherController(WeatherService service){
        this.service = service;
    }

    @GetMapping("")
    public String index(Model model) {

        var serviceResult = service.getCurrentWeather();
        model.addAttribute("weaterData", serviceResult);
        return "index";
    }

    @PostMapping("/search")
    public String search(Model model){
        var fromForm = model.getAttribute("city");
        return "otro";
    }

}
