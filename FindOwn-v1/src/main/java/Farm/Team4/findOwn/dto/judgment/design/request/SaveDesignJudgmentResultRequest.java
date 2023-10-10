package Farm.Team4.findOwn.dto.judgment.design.request;

import lombok.Data;

@Data
public class SaveDesignJudgmentResultRequest {
    private Long applicationNumber; // 출원번호
    private String applicant; // 출원인
    private int similarity; // 유사도
    private String memberId;
}
