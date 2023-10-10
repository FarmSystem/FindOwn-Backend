package Farm.Team4.findOwn.dto.judgment.design.response;

import Farm.Team4.findOwn.dto.design.ConvertDesign;
import lombok.Data;

import java.util.Date;

@Data
public class GetDesignJudgmentResponse {
    private Long designJudgmentId;
    private int similarity;
    private Date savedDate;
    private ConvertDesign design;

    public GetDesignJudgmentResponse(Long designJudgmentId, int similarity, Date savedDate, ConvertDesign design) {
        this.designJudgmentId = designJudgmentId;
        this.similarity = similarity;
        this.savedDate = savedDate;
        this.design = design;
    }
}
