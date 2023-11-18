package Farm.Team4.FindOwnv2.dto.community.post.request;

import lombok.Data;

@Data
public class UpdatePostDTO {
    private Long postId;
    private String title;
    private String tagName;
    private String content;
}
