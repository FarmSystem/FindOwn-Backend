package Farm.Team4.findOwn.dto.board.post.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SimplePostDTO {
    private Long postId;
    private String nickname;
    private String title;
    private List<String> tagNames;
    private Date createdAt;
    public SimplePostDTO(Long postId, String nickname, String title, List<String> tagNames, Date createdAt) {
        this.postId = postId;
        this.nickname = nickname;
        this.title = title;
        this.tagNames = tagNames;
        this.createdAt = createdAt;
    }
}
