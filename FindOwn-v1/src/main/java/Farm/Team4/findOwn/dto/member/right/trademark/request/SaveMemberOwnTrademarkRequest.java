package Farm.Team4.findOwn.dto.member.right.trademark.request;

import lombok.Data;

@Data
public class SaveMemberOwnTrademarkRequest {
    private String memberId;
    private String trademarkName; //  상표명
    private Long trademarkId; // 출원번호
}
