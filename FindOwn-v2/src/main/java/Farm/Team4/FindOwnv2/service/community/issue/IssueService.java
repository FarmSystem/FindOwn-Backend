package Farm.Team4.FindOwnv2.service.community.issue;

import Farm.Team4.FindOwnv2.domain.community.issue.Issue;
import Farm.Team4.FindOwnv2.domain.platform.Member;
import Farm.Team4.FindOwnv2.dto.community.issue.request.CreateIssueDTO;
import Farm.Team4.FindOwnv2.dto.community.issue.response.ShowIssueDetailDTO;
import Farm.Team4.FindOwnv2.dto.community.issue.response.ShowIssueSimpleDTO;
import Farm.Team4.FindOwnv2.exception.CustomErrorCode;
import Farm.Team4.FindOwnv2.exception.FindOwnException;
import Farm.Team4.FindOwnv2.repository.IssueRepository;
import Farm.Team4.FindOwnv2.repository.ScrapRepository;
import Farm.Team4.FindOwnv2.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;
    private final ScrapRepository scrapRepository;
    private final MemberService memberService;
    @Transactional
    public void createIssue(CreateIssueDTO createIssueDTO) {
        Issue issue = createIssueDTO.toIssue();
        issueRepository.save(issue);
        log.info("이슈 저장 완료");
    }
    public Issue findById(Long issueId){
        return issueRepository.findById(issueId)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_ISSUE));
    }
    public ShowIssueDetailDTO showIssueDetail(Long id) {
        Issue findIssue = findById(id);
        findIssue.increaseView();
        issueRepository.save(findIssue);

        Member loginMember = memberService.getMember();
        return new ShowIssueDetailDTO(
                findIssue.getId(),
                findIssue.getTitle(),
                findIssue.getCategory().toString(),
                findIssue.getContent(),
                findIssue.getSource(),
                findIssue.getReporter(),
                findIssue.getViewCnt(),
                findIssue.getScrapCnt(),
                scrapRepository.existsByMemberAndIssue(loginMember, findIssue)
        );
    }
    public List<ShowIssueSimpleDTO> showIssueSimpleList() {
        Member loginMember = memberService.getMember();
        return issueRepository.findAll().stream()
                .map(issue -> new ShowIssueSimpleDTO(
                        issue.getId(),
                        issue.getTitle(),
                        issue.getContent().substring(0,5) + "...",
                        issue.getViewCnt(),
                        issue.getScrapCnt(),
                        issue.getCreatedAt(),
                        scrapRepository.existsByMemberAndIssue(loginMember, issue)
                )).toList();
    }
    @Transactional
    public void deleteIssue(Long id) {
        issueRepository.delete(findById(id));
    }
}
