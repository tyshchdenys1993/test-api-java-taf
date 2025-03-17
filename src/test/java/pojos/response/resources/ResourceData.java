package pojos.response.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceData {
    int id;
    String name;
    int year;
    String color;
    @JsonProperty(value = "pantone_value")
    String pantoneValue;
}
