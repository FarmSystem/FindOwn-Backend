package Farm.Team4.FindOwnv2.dto.member.request;

import lombok.Data;

@Data
public class ChangeMemberIdDTO {
    private String originMemberId;
    private String newMemberId;
}
