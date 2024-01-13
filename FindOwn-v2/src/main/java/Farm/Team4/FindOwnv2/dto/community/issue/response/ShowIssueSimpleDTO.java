package Farm.Team4.FindOwnv2.dto.community.issue.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ShowIssueSimpleDTO {
    private Long id;
    private String title;
    private String simpleContent; // 축약한 글
    private int viewCnt;
    private int scrapCnt;
    private LocalDate createAt;
    private boolean isScraped;

    public ShowIssueSimpleDTO(Long id, String title, String simpleContent, int viewCnt, int scrapCnt, LocalDate createAt, boolean isScraped) {
        this.id = id;
        this.title = title;
        this.simpleContent = simpleContent;
        this.viewCnt = viewCnt;
        this.scrapCnt = scrapCnt;
        this.createAt = createAt;
        this.isScraped = isScraped;
    }
}
