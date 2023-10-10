package Farm.Team4.findOwn.dto.member.right.design.request;

import lombok.Data;

@Data
public class UpdateMemberDesignRequest {
    private Long memberOwnDesignId;
    private String memberId;
    private Long designId;
    private String image;
    private String applicant;
    private String designClass;
    private Long registerNum;
    private String state;
    private String classification;
}
