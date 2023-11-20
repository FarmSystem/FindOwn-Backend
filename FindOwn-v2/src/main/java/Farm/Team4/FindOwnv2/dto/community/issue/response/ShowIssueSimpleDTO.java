package Farm.Team4.FindOwnv2.dto.community.issue.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowIssueSimpleDTO {
    private String title;
    private String simpleContent; // 축약한 글
    private int viewCnt;
    private int scrapCnt;
    private LocalDateTime createAt;

    public ShowIssueSimpleDTO(String title, String simpleContent, int viewCnt, int scrapCnt, LocalDateTime createAt) {
        this.title = title;
        this.simpleContent = simpleContent;
        this.viewCnt = viewCnt;
        this.scrapCnt = scrapCnt;
        this.createAt = createAt;
    }
}
