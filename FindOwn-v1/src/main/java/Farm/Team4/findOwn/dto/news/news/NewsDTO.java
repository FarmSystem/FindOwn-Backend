package Farm.Team4.findOwn.dto.news.news;

import lombok.Data;

@Data
public class NewsDTO {
    private Long newsId;
    private String title;
    private String category;
    private String reporter;

    public NewsDTO(Long newsId, String title, String category, String reporter) {
        this.newsId = newsId;
        this.title = title;
        this.category = category;
        this.reporter = reporter;
    }
}
