package Farm.Team4.findOwn.repository.news;

import Farm.Team4.findOwn.domain.news.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findById(Long id);
}
