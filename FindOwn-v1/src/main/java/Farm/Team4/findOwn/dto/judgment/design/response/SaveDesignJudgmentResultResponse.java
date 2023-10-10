package Farm.Team4.findOwn.dto.judgment.design.response;

import Farm.Team4.findOwn.domain.design.Design;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveDesignJudgmentResultResponse {
    private int similarity;
    private Design design;
}
