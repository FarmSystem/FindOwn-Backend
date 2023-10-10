package Farm.Team4.findOwn.dto.board.comment.response;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {
    private Long commentId;
    private String nickname;
    private String content;
    private Date createdAt;

    public CommentDTO(Long commentId, String nickname, String content, Date createdAt) {
        this.commentId = commentId;
        this.nickname = nickname;
        this.content = content;
        this.createdAt = createdAt;
    }
}
