package Farm.Team4.findOwn.dto.judgment.trademark.response;

import Farm.Team4.findOwn.domain.trademark.Trademark;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveTrademarkJudgmentResponse {
    private int similarity; // 유사도
    private Trademark trademark;
}
