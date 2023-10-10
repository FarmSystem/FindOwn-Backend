package Farm.Team4.findOwn.dto.board.post.request;

import Farm.Team4.findOwn.domain.board.post.Post;
import Farm.Team4.findOwn.domain.board.post.Type;
import Farm.Team4.findOwn.domain.member.Member;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SavePostRequest {
    private String writerId;
    private String title;
    private String type;
    private String content;
    private List<String> tagNames;
    public Post changeToPost(Member writer){
        return new Post(this.title, Enum.valueOf(Type.class, this.type), this.content, new Date(), writer);
    }
}
