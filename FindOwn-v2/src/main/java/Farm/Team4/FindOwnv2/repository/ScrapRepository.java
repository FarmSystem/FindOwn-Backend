package Farm.Team4.FindOwnv2.repository;

import Farm.Team4.FindOwnv2.domain.community.issue.Issue;
import Farm.Team4.FindOwnv2.domain.community.issue.Scrap;
import Farm.Team4.FindOwnv2.domain.platform.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    Optional<Scrap> findById(Long scrapId);
    boolean existsByMemberAndIssue(Member member, Issue issue);
    Optional<Scrap> findByMemberAndIssue(Member member, Issue issue);
    List<Scrap> findAllByMember(Member member);
}
