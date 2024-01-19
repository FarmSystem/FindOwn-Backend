package Farm.Team4.FindOwnv2.dto.comparison.response.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;
@Builder
public record ShowOpenComparisonDto (
        @JsonProperty("comparison_id")
        Long comparisonId,
        @JsonProperty("origin_Image")
        String originImage,
        @JsonProperty("member_login")
        String memberLogin,
        @JsonProperty("created_at")
        LocalDate createdAt
){
}
