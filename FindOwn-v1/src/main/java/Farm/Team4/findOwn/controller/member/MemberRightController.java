package Farm.Team4.findOwn.controller.member;

import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.domain.member.MemberOwnDesign;
import Farm.Team4.findOwn.domain.member.MemberOwnTrademark;
import Farm.Team4.findOwn.dto.member.right.design.request.DeleteMemberOwnDesignRequest;
import Farm.Team4.findOwn.dto.member.right.design.request.SaveMemberDesignRequestInfo;
import Farm.Team4.findOwn.dto.member.right.design.request.UpdateMemberDesignRequest;
import Farm.Team4.findOwn.dto.member.right.design.response.UpdateMemberOwnDesignResponse;
import Farm.Team4.findOwn.dto.member.right.trademark.request.DeleteMemberOwnTrademarkRequest;
import Farm.Team4.findOwn.dto.member.right.trademark.request.SaveMemberOwnTrademarkRequest;
import Farm.Team4.findOwn.dto.member.right.trademark.request.UpdateMemberOwnTrademarkRequest;
import Farm.Team4.findOwn.dto.member.right.trademark.response.MemberTrademarkDTO;
import Farm.Team4.findOwn.dto.member.right.trademark.response.UpdateMemberOwnTrademarkResponse;
import Farm.Team4.findOwn.service.member.right.MemberRightService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberRightController {
    private final MemberRightService memberRightService;
    @PostMapping("/own-design")
    public String saveMemberDesign(@RequestBody SaveMemberDesignRequestInfo request){
        Long saveMemberOwnDesignId = memberRightService.saveMemberOwnDesign(request);
        log.info("회원 소유 디자인권 정보 저장 완료, 해당 아이디: " + saveMemberOwnDesignId.toString());
        return "ok";
    }
    @PostMapping("/own-trademark")
    public MemberTrademarkDTO saveMemberTrademark(@RequestBody SaveMemberOwnTrademarkRequest request) throws JsonProcessingException {
        MemberOwnTrademark memberOwnTrademark= memberRightService.saveMemberOwnTrademark(request);
        log.info("회원 소유 상표권 정보 저장 완료, 해당 아이디: " + memberOwnTrademark.getId());
        return new MemberTrademarkDTO(memberOwnTrademark.getId(), memberOwnTrademark.getTrademark().getId(), memberOwnTrademark.getTrademark().getApplicant());
    }
    @GetMapping("/own-designs")
    public List<MemberOwnDesign> memberOwnDesignList(@RequestParam String memberId){
        return memberRightService.findMemberOwnDesignList(memberId);
    }
    @GetMapping("/own-trademarks")
    public List<MemberOwnTrademark> memberOwnTrademarkList(@RequestParam String memberId){
        return memberRightService.findMemberOwnTrademarkList(memberId);
    }
    @GetMapping("/own-design")
    public MemberOwnDesign findMemberOwnDesign(@RequestParam Long ownDesignId){
        return memberRightService.findMyOwnDesign(ownDesignId);
    }
    @GetMapping("/own-trademark")
    public MemberOwnTrademark findMemberOwnTrademark(@RequestParam Long ownTrademarkId){
        return memberRightService.findMyOwnTrademark(ownTrademarkId);
    }
    @PatchMapping("/own-design")
    public UpdateMemberOwnDesignResponse updateMemberOwnDesign(@RequestBody UpdateMemberDesignRequest request){
        MemberOwnDesign memberOwnDesign = memberRightService.updateMemberOwnDesign(request);
        log.info("회원 소유 디자인권 수정 완료");
        return new UpdateMemberOwnDesignResponse(memberOwnDesign);
    }
    @PatchMapping("/own-trademark")
    public UpdateMemberOwnTrademarkResponse updateMemberOwnTrademark(@RequestBody UpdateMemberOwnTrademarkRequest request){
        MemberOwnTrademark memberOwnTrademark = memberRightService.updateMemberOwnTrademark(request);
        log.info("회원 소유 상표권 수정 완료");
        return new UpdateMemberOwnTrademarkResponse(memberOwnTrademark);
    }
    @DeleteMapping("/own-design")
    public String deleteMemberOwnDesign(@RequestBody DeleteMemberOwnDesignRequest request){
        return memberRightService.deleteMemberOwnDesign(request);
    }
    @DeleteMapping("/own-trademark")
    public String deleteMemberOwnTrademark(@RequestBody DeleteMemberOwnTrademarkRequest request){
        return memberRightService.deleteMemberOwnTrademark(request);
    }

}
