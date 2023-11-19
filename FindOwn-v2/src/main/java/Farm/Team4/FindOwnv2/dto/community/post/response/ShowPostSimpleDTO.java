package Farm.Team4.FindOwnv2.dto.community.post.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowPostSimpleDTO {
    private Long postId;
    private String title;
    private String writerId;
    private String tagName;
    private LocalDateTime createdAt;
    private int commentCnt;
    private int viewCnt;
    private int scrapCnt;

    public ShowPostSimpleDTO(Long postId, String title, String writerId, String tagName, LocalDateTime createdAt, int commentCnt, int viewCnt, int scrapCnt) {
        this.postId = postId;
        this.title = title;
        this.writerId = writerId;
        this.tagName = tagName;
        this.createdAt = createdAt;
        this.commentCnt = commentCnt;
        this.viewCnt = viewCnt;
        this.scrapCnt = scrapCnt;
    }
}
