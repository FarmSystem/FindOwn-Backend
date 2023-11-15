package Farm.Team4.FindOwnv2.dto.member.request;

import Farm.Team4.FindOwnv2.domain.platform.Member;
import lombok.Data;

@Data
public class SaveMemberDTO {
    private String id;
    private String password;
    private String korName;
    private String email;
    public Member toMember(){
        return new Member(this.id, this.password, this.korName, this.email);
    }
}
