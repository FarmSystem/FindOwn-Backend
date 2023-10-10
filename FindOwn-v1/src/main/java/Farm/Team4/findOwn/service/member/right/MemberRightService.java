package Farm.Team4.findOwn.service.member.right;

import Farm.Team4.findOwn.domain.design.Design;
import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.domain.member.MemberOwnDesign;
import Farm.Team4.findOwn.domain.member.MemberOwnTrademark;
import Farm.Team4.findOwn.domain.trademark.Trademark;
import Farm.Team4.findOwn.dto.member.right.design.request.DeleteMemberOwnDesignRequest;
import Farm.Team4.findOwn.dto.member.right.design.request.SaveMemberDesignRequestInfo;
import Farm.Team4.findOwn.dto.member.right.design.request.UpdateMemberDesignRequest;
import Farm.Team4.findOwn.dto.member.right.trademark.request.DeleteMemberOwnTrademarkRequest;
import Farm.Team4.findOwn.dto.member.right.trademark.request.SaveMemberOwnTrademarkRequest;
import Farm.Team4.findOwn.dto.member.right.trademark.request.UpdateMemberOwnTrademarkRequest;
import Farm.Team4.findOwn.dto.trademark.ConvertTrademark;
import Farm.Team4.findOwn.exception.CustomErrorCode;
import Farm.Team4.findOwn.exception.FindOwnException;
import Farm.Team4.findOwn.repository.member.MemberOwnDesignRepository;
import Farm.Team4.findOwn.repository.member.MemberOwnTrademarkRepository;
import Farm.Team4.findOwn.service.design.DesignService;
import Farm.Team4.findOwn.service.member.information.MemberService;
import Farm.Team4.findOwn.service.trademark.TrademarkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberRightService {
    private final MemberService memberService;
    private final DesignService designService;
    private final TrademarkService trademarkService;
    private final MemberOwnDesignRepository memberOwnDesignRepository;
    private final MemberOwnTrademarkRepository memberOwnTrademarkRepository;
    @Transactional
    public Long saveMemberOwnDesign(SaveMemberDesignRequestInfo request){
        Design ownDesign = request.changeToDesign();
        designService.saveDesign(ownDesign);
        log.info("디자인권 정보 저장 완료");

        Member findMember = memberService.findById(request.getMemberId());
        log.info("회원 찾기 완료");

        MemberOwnDesign savedInfo = memberOwnDesignRepository.save(new MemberOwnDesign(ownDesign, findMember));
        log.info("멤버 소유 디자인권 저장 완료");
        return savedInfo.getId();
    }
    @Transactional
    public MemberOwnTrademark saveMemberOwnTrademark(SaveMemberOwnTrademarkRequest request) throws JsonProcessingException {
        Trademark ownTrademark = trademarkService.findAndSelectOneById(request.getTrademarkName(), request.getTrademarkId());
        log.info("회원 소유 상표권 조회 성공");
        trademarkService.saveTrademark(ownTrademark);
        log.info("회원 소유 상표권, 상표권으로 저장 완료");

        Member findMember = memberService.findById(request.getMemberId());
        log.info("회원 조회 성공");

        return memberOwnTrademarkRepository.save(new MemberOwnTrademark(ownTrademark, findMember));
    }
    public List<MemberOwnDesign> findMemberOwnDesignList(String memberId){
        return memberService.findById(memberId).getOwnDesigns();
    }
    public List<MemberOwnTrademark> findMemberOwnTrademarkList(String memberId){
        return memberService.findById(memberId).getOwnTrademarks();
    }
    public MemberOwnDesign findMyOwnDesign(Long ownDesignId){
        return memberOwnDesignRepository.findById(ownDesignId)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_DESIGN_OF_MEMBER));
    }
    public MemberOwnTrademark findMyOwnTrademark(Long ownTrademarkId){
        return memberOwnTrademarkRepository.findById(ownTrademarkId)
                .orElseThrow(()-> new FindOwnException(CustomErrorCode.NOT_FOUND_TRADEMARK_OF_MEMBER));
    }
    @Transactional
    public MemberOwnDesign updateMemberOwnDesign(UpdateMemberDesignRequest request){
        MemberOwnDesign ownDesign = memberService.findById(request.getMemberId()).getOwnDesigns().stream()
                .filter(memberDesign -> memberDesign.getId().equals(request.getMemberOwnDesignId()))
                .findFirst()
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_DESIGN_OF_MEMBER));
        log.info("회원 소유 디자인권 조회 성공");
        Design updatedDesign = designService.updateDesign(ownDesign.getDesign(), request);
        log.info("디자인권 수정 성공");

        return ownDesign.updateDesign(updatedDesign);
    }
    @Transactional
    public MemberOwnTrademark updateMemberOwnTrademark(UpdateMemberOwnTrademarkRequest request){
        MemberOwnTrademark ownTrademark = memberService.findById(request.getMemberId()).getOwnTrademarks().stream()
                .filter(memberTrademark -> memberTrademark.getId().equals(request.getMemberOwnTrademarkId()))
                .findFirst()
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_TRADEMARK_OF_MEMBER));
        log.info("회원 소유 상표권 조회 완료");
        Trademark updateTrademark = trademarkService.updateTrademark(ownTrademark.getTrademark(), request);
        log.info("상표권 수정 완료");

        return ownTrademark.updateTrademark(updateTrademark);
    }
    @Transactional
    public String deleteMemberOwnDesign(DeleteMemberOwnDesignRequest request){
        MemberOwnDesign findMemberOwnDesign = memberService.findById(request.getMemberId()).getOwnDesigns().stream()
                .filter(memberOwnDesign -> memberOwnDesign.getId().equals(request.getMemberOwnDesignId()))
                .findFirst()
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_DESIGN_OF_MEMBER));
        memberOwnDesignRepository.delete(findMemberOwnDesign);
        log.info("회원 소유 디자인권 삭제 완료");
        return "ok";
    }
    @Transactional
    public String deleteMemberOwnTrademark(DeleteMemberOwnTrademarkRequest request){
        MemberOwnTrademark findMemberOwnTrademark = memberService.findById(request.getMemberId()).getOwnTrademarks().stream()
                .filter(memberOwnTrademark -> memberOwnTrademark.getId().equals(request.getMemberOwnTrademarkId()))
                .findFirst()
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_TRADEMARK_OF_MEMBER));
        memberOwnTrademarkRepository.delete(findMemberOwnTrademark);
        log.info("회원 소유 상표권 삭제 완료");
        return "ok";
    }
}
