package Farm.Team4.findOwn.dto.board.post.request;

import lombok.Data;

import java.util.List;

@Data
public class UpdatePostRequest {
    private String writerId;
    private String title;
    private String type;
    private String content;
    private List<String> tagNames;
}
