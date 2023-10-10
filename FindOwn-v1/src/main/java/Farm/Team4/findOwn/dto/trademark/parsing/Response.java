package Farm.Team4.findOwn.dto.trademark.parsing;

import Farm.Team4.findOwn.dto.trademark.parsing.body.Body;
import Farm.Team4.findOwn.dto.trademark.parsing.count.Count;
import Farm.Team4.findOwn.dto.trademark.parsing.header.Header;
import lombok.Data;

@Data
public class Response {
    private Header header;
    private Body body;
    private Count count;
}
