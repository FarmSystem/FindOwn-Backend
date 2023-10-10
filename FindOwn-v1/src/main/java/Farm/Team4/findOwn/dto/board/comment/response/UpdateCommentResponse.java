package Farm.Team4.findOwn.dto.board.comment.response;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateCommentResponse {
    private Long commentId;
    private String nickname;
    private String content;
    private Date updatedAt;

    public UpdateCommentResponse(Long commentId, String nickname, String content, Date updatedAt) {
        this.commentId = commentId;
        this.nickname = nickname;
        this.content = content;
        this.updatedAt = updatedAt;
    }
}
