package Farm.Team4.findOwn.dto.board.comment.request;

import lombok.Data;

@Data
public class SaveCommentRequest {
    private Long postId;
    private String nickname;
    private String content;
}
