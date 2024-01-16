package Farm.Team4.FindOwnv2.dto.comparison.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public record JudgementAIDto (
        String result,
        Float accuracy,
        String ApplicationNumber,
        String ApplicantName,
        String Title,
        String ImagePath
){
}
