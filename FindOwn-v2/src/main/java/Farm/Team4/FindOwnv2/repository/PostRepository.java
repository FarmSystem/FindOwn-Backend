package Farm.Team4.FindOwnv2.repository;

import Farm.Team4.FindOwnv2.domain.community.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
