package Farm.Team4.FindOwnv2.dto.community.post.response;

import Farm.Team4.FindOwnv2.dto.community.comment.response.ShowCommentDTO;
import lombok.Data;

import java.util.List;

@Data
public class ShowPostDetailDTO {
    private Long postId;
    private String title;
    private String writer;
    private String tag;
    private String content;
    private int viewCnt;
    private List<ShowCommentDTO> comments;

    public ShowPostDetailDTO(Long id, String title, String writer, String tag, String content, int viewCnt, List<ShowCommentDTO> comments) {
        this.postId = id;
        this.title = title;
        this.writer = writer;
        this.tag = tag;
        this.content = content;
        this.viewCnt = viewCnt;
        this.comments = comments;
    }
}
