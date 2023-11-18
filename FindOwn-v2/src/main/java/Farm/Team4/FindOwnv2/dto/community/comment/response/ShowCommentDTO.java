package Farm.Team4.FindOwnv2.dto.community.comment.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowCommentDTO {
    private Long commentId;
    private String writer;
    private String content;
    private LocalDateTime createdAt;

    public ShowCommentDTO(Long commentId, String writer, String content, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.writer = writer;
        this.content = content;
        this.createdAt = createdAt;
    }
}
