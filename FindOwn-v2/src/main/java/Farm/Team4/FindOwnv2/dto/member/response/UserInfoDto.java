package Farm.Team4.FindOwnv2.dto.member.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;
@Builder
public record UserInfoDto (
        @JsonProperty("korName")
        String korName,
        @JsonProperty("email")
        String email,
        @JsonProperty("password_update_date")
        LocalDate passwordUpdateDate){
}
