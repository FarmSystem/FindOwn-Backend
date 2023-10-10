package Farm.Team4.findOwn.dto.judgment.design.request;

import lombok.Data;

@Data
public class DeleteDesignJudgmentRequest {
    private String memberId;
    private Long designJudgmentId;
}
