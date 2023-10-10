package Farm.Team4.findOwn.dto.design.parsing.body;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Body {
    private List<Item> items;
}
