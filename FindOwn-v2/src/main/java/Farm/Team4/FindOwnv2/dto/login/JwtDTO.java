package Farm.Team4.FindOwnv2.dto.login;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
@Getter
@Builder
public class JwtDTO {
    private String accessToken;
    private String refreshToken;
    private Date createAt;
    private Date updateAt;
}
