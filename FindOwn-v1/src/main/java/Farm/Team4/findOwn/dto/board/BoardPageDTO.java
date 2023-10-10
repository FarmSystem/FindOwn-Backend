package Farm.Team4.findOwn.dto.board;

import Farm.Team4.findOwn.dto.board.post.response.SimplePostDTO;
import lombok.Data;

import java.util.List;

@Data
public class BoardPageDTO {
    private Long postCount;
    private List<SimplePostDTO> posts;

    public BoardPageDTO(Long postCount, List<SimplePostDTO> posts) {
        this.postCount = postCount;
        this.posts = posts;
    }
}
