package Farm.Team4.FindOwnv2.dto.comparison.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record ComparisonResultDto(
        @JsonProperty("trademark_infos")
        List<ComparisonTrademarkDto> result

) {
}
