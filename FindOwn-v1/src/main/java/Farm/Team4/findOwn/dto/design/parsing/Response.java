package Farm.Team4.findOwn.dto.design.parsing;

import Farm.Team4.findOwn.dto.design.parsing.body.Body;
import Farm.Team4.findOwn.dto.design.parsing.count.Count;
import Farm.Team4.findOwn.dto.design.parsing.header.Header;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private Header header;
    private Body body;
    private Count count;
}
