package Farm.Team4.findOwn.controller.design;

import Farm.Team4.findOwn.domain.design.Design;
import Farm.Team4.findOwn.dto.design.parsing.body.Item;
import Farm.Team4.findOwn.service.design.DesignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DesignController {
    private final DesignService designService;
    @GetMapping("/find/design")
    public List<Design> findDesign(@RequestParam String articleName) throws IOException {
        List<Item> apiResult = designService.findDesign(articleName);
        return designService.selectRegisteredTrademark(apiResult);
    }
}
