package Farm.Team4.FindOwnv2.dto.comparison.response.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ComparisonResultDto (
        @JsonProperty("input_image")
        String originImage,
        @JsonProperty("trademark")
        List<ComparisonTrademarkDto> trademarks
){
}
