package Farm.Team4.findOwn.dto.member.information;

import lombok.Getter;

@Getter
public class ChangePasswordRequest {
    private String id;
    private String oldPassword;
    private String newPassword;
}
