package Farm.Team4.findOwn.controller.judgment.trademark;

import Farm.Team4.findOwn.dto.judgment.design.request.DeleteDesignJudgmentRequest;
import Farm.Team4.findOwn.dto.judgment.trademark.request.DeleteTrademarkJudgmentRequest;
import Farm.Team4.findOwn.dto.judgment.trademark.response.GetTrademarkJudgmentResponse;
import Farm.Team4.findOwn.dto.judgment.trademark.response.SaveTrademarkJudgmentResponse;
import Farm.Team4.findOwn.dto.judgment.trademark.request.SaveTrademarkJudgmentRequest;
import Farm.Team4.findOwn.service.judgment.TrademarkJudgmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class TrademarkJudgmentController {
    private final TrademarkJudgmentService trademarkJudgmentService;
    @PostMapping("/member/trademark-judgment")
    public SaveTrademarkJudgmentResponse showJudgmentResult(@RequestBody SaveTrademarkJudgmentRequest result) throws JsonProcessingException {
        return trademarkJudgmentService.showTrademarkJudgment(result);
    }
    @GetMapping("/member/{memberId}/trademark-judgment")
    public List<GetTrademarkJudgmentResponse> findMemberOwnTrademarkJudgment(@PathVariable String memberId){
        return trademarkJudgmentService.findMemberOwnTrademarkJudgment(memberId);
    }
    @DeleteMapping("/member/trademark-judgment")
    public String deleteMemberOwnTrademarkJudgment(@RequestBody DeleteTrademarkJudgmentRequest request){
        return trademarkJudgmentService.deleteMemberOwnTrademarkJudgment(request.getMemberId(), request.getTrademarkJudgmentId());
    }
}
