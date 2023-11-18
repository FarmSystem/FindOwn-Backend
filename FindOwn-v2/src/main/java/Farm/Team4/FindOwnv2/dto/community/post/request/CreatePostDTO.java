package Farm.Team4.FindOwnv2.dto.community.post.request;

import Farm.Team4.FindOwnv2.domain.community.board.Post;
import Farm.Team4.FindOwnv2.domain.community.board.Tag;
import Farm.Team4.FindOwnv2.domain.platform.Member;
import lombok.Data;

@Data
public class CreatePostDTO {
    private String title;
    private String tagName;
    private String content;
    public Post toPost(Member writer){
        return new Post(writer, this.title, Tag.valueOf(this.tagName), this.content);
    }
}
