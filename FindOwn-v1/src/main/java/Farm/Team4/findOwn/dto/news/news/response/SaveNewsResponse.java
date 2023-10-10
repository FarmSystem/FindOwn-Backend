package Farm.Team4.findOwn.dto.news.news.response;

import Farm.Team4.findOwn.domain.news.News;
import lombok.Data;

@Data
public class SaveNewsResponse {
    private Long newsId;
    private String title;
    private String content;
    private String category;
    private String source;
    private String reporter;

    public SaveNewsResponse(Long newsId, News news) {
        this.newsId = newsId;
        this.title = news.getTitle();
        this.content = news.getContent();
        this.category = String.valueOf(news.getCategory());
        this.source = news.getSource();
        this.reporter = news.getReporter();
    }
}
