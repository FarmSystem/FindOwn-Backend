package Farm.Team4.findOwn.service.judgment;

import Farm.Team4.findOwn.domain.judgment.TrademarkJudgment;
import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.domain.trademark.Trademark;
import Farm.Team4.findOwn.dto.judgment.trademark.response.GetTrademarkJudgmentResponse;
import Farm.Team4.findOwn.dto.judgment.trademark.response.SaveTrademarkJudgmentResponse;
import Farm.Team4.findOwn.dto.judgment.trademark.request.SaveTrademarkJudgmentRequest;
import Farm.Team4.findOwn.dto.trademark.ConvertTrademark;
import Farm.Team4.findOwn.exception.CustomErrorCode;
import Farm.Team4.findOwn.exception.FindOwnException;
import Farm.Team4.findOwn.repository.judgment.TrademarkJudgmentRepository;
import Farm.Team4.findOwn.service.member.information.MemberService;
import Farm.Team4.findOwn.service.trademark.TrademarkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class TrademarkJudgmentService {
    private final MemberService memberService;
    private final TrademarkService trademarkService;
    private final TrademarkJudgmentRepository trademarkJudgmentRepository;
    public SaveTrademarkJudgmentResponse showTrademarkJudgment(SaveTrademarkJudgmentRequest result) throws JsonProcessingException {
        Member findMember = memberService.findById(result.getMemberId());
        Trademark findTrademark = findAndSelectOne(result.getApplicant());
        saveTrademarkJudgment(result.getSimilarity(), findTrademark, findMember);

        return new SaveTrademarkJudgmentResponse(result.getSimilarity(), findTrademark);
    }
    private Trademark findAndSelectOne(String applicantName) throws JsonProcessingException {
        return trademarkService.findAndSelectOneByApplicant(applicantName);
    }
    @Transactional
    public TrademarkJudgment saveTrademarkJudgment(int similarity, Trademark trademark, Member member){
        return trademarkJudgmentRepository.save(new TrademarkJudgment(similarity, member, trademark));
    }
    public List<GetTrademarkJudgmentResponse> findMemberOwnTrademarkJudgment(String memberId){
        return memberService.findById(memberId).getTrademarkJudgments().stream()
                .map(trademarkJudgment -> new GetTrademarkJudgmentResponse(
                        trademarkJudgment.getId(),
                        trademarkJudgment.getResult(),
                        trademarkJudgment.getSavedDate(),
                        new ConvertTrademark(
                                trademarkJudgment.getTrademark().getId(),
                                trademarkJudgment.getTrademark().getImage(),
                                trademarkJudgment.getTrademark().getApplicant(),
                                trademarkJudgment.getTrademark().getRegisterNum(),
                                trademarkJudgment.getTrademark().getState(),
                                trademarkJudgment.getTrademark().getClassification()
                        )
                )).toList();
    }
    @Transactional
    public String deleteMemberOwnTrademarkJudgment(String memberId, Long trademarkJudgmentId) {
        TrademarkJudgment findTrademarkJudgment = memberService.findById(memberId).getTrademarkJudgments().stream()
                .filter(trademarkJudgment -> trademarkJudgment.getId().equals(trademarkJudgmentId))
                .findFirst()
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_TRADEMARK_JUDGMENT));
        log.info("상표권 침해 판단 결과 조회 성공");
        trademarkJudgmentRepository.delete(findTrademarkJudgment);
        log.info("상표권 침해 판단 결과 삭제 성공");
        return "delete complete";
    }
}
