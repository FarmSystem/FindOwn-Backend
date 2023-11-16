package Farm.Team4.FindOwnv2.dto.auth.request;

import lombok.Data;

@Data
public class VerifyEmailCodeDTO {
    private String email;
    private String code;
}
