package Farm.Team4.findOwn.controller.judgment.design;

import Farm.Team4.findOwn.domain.judgment.DesignJudgment;
import Farm.Team4.findOwn.dto.design.ConvertDesign;
import Farm.Team4.findOwn.dto.judgment.design.request.DeleteDesignJudgmentRequest;
import Farm.Team4.findOwn.dto.judgment.design.request.SaveDesignJudgmentResultRequest;
import Farm.Team4.findOwn.dto.judgment.design.response.GetDesignJudgmentResponse;
import Farm.Team4.findOwn.dto.judgment.design.response.SaveDesignJudgmentResultResponse;
import Farm.Team4.findOwn.service.judgment.DesignJudgmentService;
import Farm.Team4.findOwn.service.member.information.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class DesignJudgmentController {
    private final DesignJudgmentService designJudgmentService;
    @PostMapping("/member/design-judgment")
    public SaveDesignJudgmentResultResponse showDesignJudgmentResult(@RequestBody SaveDesignJudgmentResultRequest result) throws IOException {
        return designJudgmentService.showDesignJudgmentResult(result);
    }
    @GetMapping("/member/{memberId}/design-judgment")
    public List<GetDesignJudgmentResponse> findMemberOwnDesignJudgment(@PathVariable String memberId){
       return designJudgmentService.findMemberOwnDesignJudgment(memberId);
    }
    @DeleteMapping("/member/design-judgment")
    public String deleteMemberOwnDesignJudgment(@RequestBody DeleteDesignJudgmentRequest request){
        return designJudgmentService.deleteDesignJudgment(request);
    }
}
