package Farm.Team4.findOwn.dto.member;

import lombok.Data;

import java.util.Date;

@Data
public class MemberDTO {
    private String memberId;
    private String email;
    private String name;
    private Date membershipDate;

    public MemberDTO(String memberId, String email, String name, Date membershipDate) {
        this.memberId = memberId;
        this.email = email;
        this.name = name;
        this.membershipDate = membershipDate;
    }
}
