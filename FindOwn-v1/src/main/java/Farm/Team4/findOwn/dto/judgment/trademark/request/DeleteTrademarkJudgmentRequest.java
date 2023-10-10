package Farm.Team4.findOwn.dto.judgment.trademark.request;

import lombok.Data;

@Data
public class DeleteTrademarkJudgmentRequest {
    private String memberId;
    private Long trademarkJudgmentId;
}
