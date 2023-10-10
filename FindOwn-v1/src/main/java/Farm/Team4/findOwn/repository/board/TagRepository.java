package Farm.Team4.findOwn.repository.board;

import Farm.Team4.findOwn.domain.board.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByName(String tagName);
    Optional<Tag> findByName(String tagName);
}
