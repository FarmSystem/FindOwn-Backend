package Farm.Team4.findOwn.dto.board.comment.response;

import lombok.Data;

import java.util.Date;

@Data
public class SaveCommentResponse {
    private Long commentId;
    private String nickname;
    private String comment;
    private Date createdAt;

    public SaveCommentResponse(Long commentId, String nickname, String comment, Date createdAt) {
        this.commentId = commentId;
        this.nickname = nickname;
        this.comment = comment;
        this.createdAt = createdAt;
    }
}
