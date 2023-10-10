package Farm.Team4.findOwn.dto.member.information;

import lombok.Data;

@Data
public class VerifyMemberRequest {
    private String email;
    private String code;
}
