package Farm.Team4.findOwn.dto.board.post.response;

import lombok.Data;

import java.util.Date;

@Data
public class SavePostResponse {
    private Long postId;
    private String title;
    private String content;
    private Date createdAt;
    public SavePostResponse(Long postId, String title, String content, Date createdAt) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
