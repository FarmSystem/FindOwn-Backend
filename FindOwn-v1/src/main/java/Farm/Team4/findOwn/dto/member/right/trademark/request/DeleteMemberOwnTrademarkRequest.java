package Farm.Team4.findOwn.dto.member.right.trademark.request;

import lombok.Data;

@Data
public class DeleteMemberOwnTrademarkRequest {
    private String memberId;
    private Long memberOwnTrademarkId;
}
