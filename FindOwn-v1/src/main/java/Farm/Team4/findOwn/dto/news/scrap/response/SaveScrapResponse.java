package Farm.Team4.findOwn.dto.news.scrap.response;

import lombok.Data;

@Data
public class SaveScrapResponse {
    private Long scrapId;
    private String memberId;
    private Long newsId;

    public SaveScrapResponse(Long scrapId, String memberId, Long newsId) {
        this.scrapId = scrapId;
        this.memberId = memberId;
        this.newsId = newsId;
    }
}
