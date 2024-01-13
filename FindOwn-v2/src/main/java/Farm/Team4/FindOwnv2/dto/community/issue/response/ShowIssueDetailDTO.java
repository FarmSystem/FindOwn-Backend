package Farm.Team4.FindOwnv2.dto.community.issue.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ShowIssueDetailDTO {
    private Long issueId;
    private String title;
    private String category;
    private String content;
    private String source;
    private String reporter;
    private int viewCnt;
    private int ScrapCnt;
    @JsonProperty("is_scraped")
    private boolean isScraped;

    public ShowIssueDetailDTO(Long issueId, String title, String category, String content, String source, String reporter, int viewCnt, int scrapCnt, boolean isScraped) {
        this.issueId = issueId;
        this.title = title;
        this.category = category;
        this.content = content;
        this.source = source;
        this.reporter = reporter;
        this.viewCnt = viewCnt;
        this.ScrapCnt = scrapCnt;
        this.isScraped = isScraped;
    }
}
