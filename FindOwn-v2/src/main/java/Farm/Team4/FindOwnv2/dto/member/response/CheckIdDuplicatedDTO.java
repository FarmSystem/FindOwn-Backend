package Farm.Team4.FindOwnv2.dto.member.response;

import lombok.Data;

@Data
public class CheckIdDuplicatedDTO {
    private boolean notDuplicated;

    public CheckIdDuplicatedDTO(boolean notDuplicated) {
        this.notDuplicated = notDuplicated;
    }
}
