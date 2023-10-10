package Farm.Team4.findOwn.dto.member.right.trademark.response;

import lombok.Data;

@Data
public class MemberTrademarkDTO {
    private Long memberTrademarkId;
    private Long trademarkId;
    private String applicant;

    public MemberTrademarkDTO(Long memberTrademarkId, Long trademarkId, String applicant) {
        this.memberTrademarkId = memberTrademarkId;
        this.trademarkId = trademarkId;
        this.applicant = applicant;
    }
}
