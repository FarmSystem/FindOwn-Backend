package Farm.Team4.findOwn.controller.trademark;
import Farm.Team4.findOwn.domain.trademark.Trademark;
import Farm.Team4.findOwn.dto.trademark.parsing.body.Item;
import Farm.Team4.findOwn.service.trademark.TrademarkService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TrademarkController {
    private final TrademarkService trademarkService;
    @GetMapping("/find/trademark")
    public List<Trademark> findTrademark(@RequestParam String searchString) throws JsonProcessingException {
        List<Item> trademarksDto = trademarkService.findTrademark(searchString);
        return trademarkService.selectRegisteredTrademark(trademarksDto);
    }
}
