package Farm.Team4.FindOwnv2.service.community.scrap;

import Farm.Team4.FindOwnv2.domain.community.issue.Issue;
import Farm.Team4.FindOwnv2.domain.community.issue.Scrap;
import Farm.Team4.FindOwnv2.domain.platform.Member;
import Farm.Team4.FindOwnv2.exception.CustomErrorCode;
import Farm.Team4.FindOwnv2.exception.FindOwnException;
import Farm.Team4.FindOwnv2.repository.ScrapRepository;
import Farm.Team4.FindOwnv2.service.community.issue.IssueService;
import Farm.Team4.FindOwnv2.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapRepository scrapRepository;
    private final MemberService memberService;
    private final IssueService issueService;
    @Transactional
    public void createScrap(Long issueId) {
        Issue findIssue = issueService.findById(issueId);
        findIssue.increaseScrap();

        Member findMember = getMember();
        scrapRepository.save(new Scrap(findMember, findIssue));
    }
    public Scrap findById(Long scrapId){
        return scrapRepository.findById(scrapId)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_SCRAP));
    }
    @Transactional
    public void deleteScrap(Long id) {
        Scrap findScrap = findById(id);
        if (!findScrap.getMember().equals(getMember()))
            throw new FindOwnException(CustomErrorCode.NOT_MATCH_WRITER);
        scrapRepository.delete(findScrap);
    }
    private Member getMember(){
        return memberService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }


}
