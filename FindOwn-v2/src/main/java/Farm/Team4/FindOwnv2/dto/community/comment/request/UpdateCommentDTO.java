package Farm.Team4.FindOwnv2.dto.community.comment.request;

import lombok.Data;

@Data
public class UpdateCommentDTO {
    private Long commentId;
    private String writerId;
    private String content;
}
