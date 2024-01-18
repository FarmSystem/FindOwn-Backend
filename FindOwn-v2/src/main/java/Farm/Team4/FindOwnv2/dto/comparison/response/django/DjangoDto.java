package Farm.Team4.FindOwnv2.dto.comparison.response.django;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record DjangoDto(
        @JsonProperty("result")
        List<JudgementAIDto> trademark

) {
}
