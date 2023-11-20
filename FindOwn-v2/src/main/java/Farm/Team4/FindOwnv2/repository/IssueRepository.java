package Farm.Team4.FindOwnv2.repository;

import Farm.Team4.FindOwnv2.domain.community.issue.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    Optional<Issue> findById(Long issueId);
}
