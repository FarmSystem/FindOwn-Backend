package Farm.Team4.findOwn.dto.member.right.trademark.request;

import lombok.Data;

@Data
public class UpdateMemberOwnTrademarkRequest {
    private Long memberOwnTrademarkId;
    private String memberId;
    private Long trademarkId;
    private String image;
    private String applicant;
    private Long registerNum;
    private String state;
    private String classification;
}
