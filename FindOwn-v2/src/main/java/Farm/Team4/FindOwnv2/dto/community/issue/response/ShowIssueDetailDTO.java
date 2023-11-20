package Farm.Team4.FindOwnv2.dto.community.issue.response;

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

    public ShowIssueDetailDTO(Long issueId, String title, String category, String content, String source, String reporter, int viewCnt, int scrapCnt) {
        this.issueId = issueId;
        this.title = title;
        this.category = category;
        this.content = content;
        this.source = source;
        this.reporter = reporter;
        this.viewCnt = viewCnt;
        ScrapCnt = scrapCnt;
    }
}
