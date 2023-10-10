package Farm.Team4.findOwn.service.judgment;

import Farm.Team4.findOwn.domain.design.Design;
import Farm.Team4.findOwn.domain.judgment.DesignJudgment;
import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.dto.design.ConvertDesign;
import Farm.Team4.findOwn.dto.judgment.design.request.DeleteDesignJudgmentRequest;
import Farm.Team4.findOwn.dto.judgment.design.request.SaveDesignJudgmentResultRequest;
import Farm.Team4.findOwn.dto.judgment.design.response.GetDesignJudgmentResponse;
import Farm.Team4.findOwn.dto.judgment.design.response.SaveDesignJudgmentResultResponse;
import Farm.Team4.findOwn.exception.CustomErrorCode;
import Farm.Team4.findOwn.exception.FindOwnException;
import Farm.Team4.findOwn.repository.judgment.DesignJudgmentRepository;
import Farm.Team4.findOwn.service.design.DesignService;
import Farm.Team4.findOwn.service.member.information.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DesignJudgmentService {
    private final MemberService memberService;
    private final DesignService designService;
    private final DesignJudgmentRepository designJudgmentRepository;
    public SaveDesignJudgmentResultResponse showDesignJudgmentResult(SaveDesignJudgmentResultRequest result) throws IOException {
        Member findMember = memberService.findById(result.getMemberId());
        Design findDesign = findAndSelectOne(result.getApplicant());

        saveTrademarkJudgment(result.getSimilarity(), findMember, findDesign);
        log.info("디자인 판단 결과 저장 완료");

        return new SaveDesignJudgmentResultResponse(result.getSimilarity(), findDesign);
    }
    private Design findAndSelectOne(String articleName) throws IOException {
        return designService.findAndSelectOne(articleName);
    }
    @Transactional
    public DesignJudgment saveTrademarkJudgment(int similarity, Member member, Design design){
        return designJudgmentRepository.save(new DesignJudgment(similarity, member, design));
    }
    public List<GetDesignJudgmentResponse> findMemberOwnDesignJudgment(String memberId){
        return memberService.findById(memberId).getDesignJudgments().stream()
                .map(designJudgment -> new GetDesignJudgmentResponse(
                                designJudgment.getId(),
                                designJudgment.getResult(),
                                designJudgment.getSavedDate(),
                                new ConvertDesign(
                                        designJudgment.getDesign().getId(),
                                        designJudgment.getDesign().getImage(),
                                        designJudgment.getDesign().getApplicant(),
                                        designJudgment.getDesign().getDesignClass(),
                                        designJudgment.getDesign().getRegisterNum(),
                                        designJudgment.getDesign().getState(),
                                        designJudgment.getDesign().getClassification())
                        )
                ).toList();
    }
    @Transactional
    public String deleteDesignJudgment(DeleteDesignJudgmentRequest request){
        DesignJudgment findDesignJudgment = memberService.findById(request.getMemberId()).getDesignJudgments().stream()
                .filter(designJudgment -> designJudgment.getId().equals(request.getDesignJudgmentId()))
                .findFirst()
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_DESIGN_JUDGMENT));
        log.info("특정 디자인 침해 판단 조회 성공");
        designJudgmentRepository.delete(findDesignJudgment);
        log.info("디자인 침해 판단 삭제 성공");
        return "delete complete";
    }

}
