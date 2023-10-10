package Farm.Team4.findOwn.repository.news;

import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.domain.news.News;
import Farm.Team4.findOwn.domain.news.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    boolean existsByMemberAndNews(Member member, News news);
    Optional<Scrap> findById(Long id);
}
