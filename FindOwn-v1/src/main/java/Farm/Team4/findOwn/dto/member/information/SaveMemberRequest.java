package Farm.Team4.findOwn.dto.member.information;

import Farm.Team4.findOwn.domain.member.Member;
import lombok.Data;

import java.util.Date;

@Data
public class SaveMemberRequest {
    private String id;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
    private String email;
    public Member toMember(Date now){
        return new Member(
                this.id,
                this.password,
                this.name,
                this.nickname,
                this.phoneNumber,
                this.email,
                now);
    }
}
