package pe.isil.weather_app.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Current(
        @JsonProperty("temp_c")
        Double tempC,
        @JsonProperty("temp_f")
        Double tempF,
        Condition condition
) {
}
