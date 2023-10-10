package Farm.Team4.findOwn.controller.news;

import Farm.Team4.findOwn.dto.news.scrap.request.SaveScrapRequest;
import Farm.Team4.findOwn.dto.news.scrap.response.SaveScrapResponse;
import Farm.Team4.findOwn.service.news.ScrapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScrapController {
    private final ScrapService scrapService;
    @PostMapping("/scrap")
    public SaveScrapResponse saveScrap(@RequestBody SaveScrapRequest request){
        return scrapService.saveScrap(request);
    }
    @DeleteMapping("/scrap/{scrapId}")
    public void deleteScrap(@PathVariable Long scrapId){
        scrapService.deleteScrap(scrapId);
    }
}
