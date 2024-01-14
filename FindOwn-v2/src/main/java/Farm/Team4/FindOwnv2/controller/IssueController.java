package Farm.Team4.FindOwnv2.controller;

import Farm.Team4.FindOwnv2.dto.community.issue.request.CreateIssueDTO;
import Farm.Team4.FindOwnv2.dto.community.issue.response.ShowIssueDetailDTO;
import Farm.Team4.FindOwnv2.dto.community.issue.response.ShowIssueSimpleDTO;
import Farm.Team4.FindOwnv2.service.community.issue.IssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v2/users/community/issue")
@RequiredArgsConstructor
public class IssueController {
    private final IssueService issueService;
    @PostMapping
    public void createIssue(@RequestBody CreateIssueDTO createIssueDTO){
        issueService.createIssue(createIssueDTO);
    }
    @GetMapping("/")
    public ShowIssueDetailDTO showIssueDetail(@RequestParam Long id){
        return issueService.showIssueDetail(id);
    }
    @GetMapping()
    public List<ShowIssueSimpleDTO> showIssueSimpleList(){
        return issueService.showIssueSimpleList();
    }

    @DeleteMapping("/")
    public void deleteIssue(@RequestParam Long id){
        issueService.deleteIssue(id);
    }

}
