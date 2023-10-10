package Farm.Team4.findOwn.dto.member.right.trademark.response;

import Farm.Team4.findOwn.domain.member.MemberOwnTrademark;
import Farm.Team4.findOwn.domain.trademark.Trademark;
import lombok.Data;

@Data
public class UpdateMemberOwnTrademarkResponse {
    private Long memberOwnTrademarkId;
    private String image;
    private String applicant;
    private Long registerNum;
    private String state;
    private String classification;
    public UpdateMemberOwnTrademarkResponse(MemberOwnTrademark memberOwnTrademark){
        Trademark trademark = memberOwnTrademark.getTrademark();
        this.memberOwnTrademarkId = memberOwnTrademark.getId();
        this.image = trademark.getImage();
        this.applicant = trademark.getApplicant();
        this.registerNum = trademark.getRegisterNum();
        this.state = trademark.getState();
        this.classification = trademark.getClassification();
    }
}
