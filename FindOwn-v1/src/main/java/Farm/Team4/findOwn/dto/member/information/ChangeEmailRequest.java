package Farm.Team4.findOwn.dto.member.information;

import lombok.Data;

@Data
public class ChangeEmailRequest {
    private String oldEmail;
    private String newEmail;
}
