package Farm.Team4.findOwn.dto.board.post.response;

import lombok.Data;

import java.util.Date;

@Data
public class UpdatePostResponse {
    private Long postId;
    private String title;
    private String content;
    private Date updatedAt;
    public UpdatePostResponse(Long postId, String title, String content, Date updatedAt) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.updatedAt = updatedAt;
    }
}
