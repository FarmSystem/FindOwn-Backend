package Farm.Team4.FindOwnv2.dto.community.comment.request;

import Farm.Team4.FindOwnv2.domain.community.board.Comment;
import Farm.Team4.FindOwnv2.domain.community.board.Post;
import Farm.Team4.FindOwnv2.domain.platform.Member;
import lombok.Data;

@Data
public class CreateCommentDTO {
    private Long postId;
    private String content;
    public Comment toComment(Member writer, Post post){
        return new Comment(writer, post, this.content);
    }
}
