package Farm.Team4.FindOwnv2.controller;

import Farm.Team4.FindOwnv2.service.community.scrap.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/users/community/scrap")
@RequiredArgsConstructor
public class ScrapController {
    private final ScrapService scrapService;
    @PostMapping("/")
    public void createScarp(@RequestParam Long id){
        scrapService.createScrap(id);
    }
    @DeleteMapping("/")
    public void deleteScrap(@RequestParam Long issueId){
        scrapService.deleteScrap(issueId);
    }

}
