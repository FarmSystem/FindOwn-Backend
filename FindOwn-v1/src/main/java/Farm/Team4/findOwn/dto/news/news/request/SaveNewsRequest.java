package Farm.Team4.findOwn.dto.news.news.request;

import lombok.Data;

@Data
public class SaveNewsRequest {
    private String title;
    private String content;
    private String category;
    private String source;
    private String reporter;
}
