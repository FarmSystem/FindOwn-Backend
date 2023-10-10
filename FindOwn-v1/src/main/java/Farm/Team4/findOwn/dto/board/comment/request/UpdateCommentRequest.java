package Farm.Team4.findOwn.dto.board.comment.request;

import lombok.Data;

@Data
public class UpdateCommentRequest {
    private Long commentId;
    private String nickname;
    private String content;
}
