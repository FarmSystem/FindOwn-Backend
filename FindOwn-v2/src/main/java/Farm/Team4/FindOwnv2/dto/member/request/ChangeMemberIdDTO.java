package Farm.Team4.FindOwnv2.dto.member.request;

import lombok.Data;

@Data
public class ChangeMemberIdDTO {
    private String originNickname;
    private String newNickname;
}
