package Farm.Team4.FindOwnv2.dto.member.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record UserScrapInfoDto (
        @JsonProperty("issue_id")
        Long issueId,
        @JsonProperty("issue_title")
        String issueTitle,
        @JsonProperty("category")
        String category,
        @JsonProperty("year")
        int year,
        @JsonProperty("month")
        int month,
        @JsonProperty("day")
        int day,
        @JsonProperty("view_cnt")
        int viewCnt,
        @JsonProperty("scrap_cnt")
        int scrapCnt){

}
