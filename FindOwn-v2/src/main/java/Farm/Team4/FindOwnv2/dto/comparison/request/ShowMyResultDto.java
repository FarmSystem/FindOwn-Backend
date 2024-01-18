package Farm.Team4.FindOwnv2.dto.comparison.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDate;
@Builder
public record ShowMyResultDto (
        @JsonProperty("comparison_id")
        Long comparisonId,
        @JsonProperty("image_path")
        String originImage,
        @JsonProperty("created_at")
        LocalDate createdAt,
        @JsonProperty("member_id")
        Long memberId,
        @JsonProperty("open")
        boolean open

){
}
