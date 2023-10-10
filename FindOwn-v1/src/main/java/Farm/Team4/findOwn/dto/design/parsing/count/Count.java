package Farm.Team4.findOwn.dto.design.parsing.count;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Count {
    private int numOfRows;
    private int pageNo;
    private int totalCount;
}
