package Farm.Team4.FindOwnv2.dto.auth.response;

import lombok.Data;

@Data
public class ResultPasswordDTO {
    private boolean match;

    public ResultPasswordDTO(boolean match) {
        this.match = match;
    }
}
