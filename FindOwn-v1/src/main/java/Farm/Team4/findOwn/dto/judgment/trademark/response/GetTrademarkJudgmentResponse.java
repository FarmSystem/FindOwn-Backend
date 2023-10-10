package Farm.Team4.findOwn.dto.judgment.trademark.response;

import Farm.Team4.findOwn.dto.trademark.ConvertTrademark;
import lombok.Data;

import java.util.Date;

@Data
public class GetTrademarkJudgmentResponse {
    private Long trademarkJudgmentId;
    private int similarity;
    private Date savedDate;
    private ConvertTrademark trademark;

    public GetTrademarkJudgmentResponse(Long trademarkJudgmentId, int similarity, Date savedDate, ConvertTrademark trademark) {
        this.trademarkJudgmentId = trademarkJudgmentId;
        this.similarity = similarity;
        this.savedDate = savedDate;
        this.trademark = trademark;
    }
}
