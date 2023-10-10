package Farm.Team4.findOwn.dto.member.right.design.request;

import lombok.Data;

@Data
public class DeleteMemberOwnDesignRequest {
    private String memberId;
    private Long memberOwnDesignId;
}
