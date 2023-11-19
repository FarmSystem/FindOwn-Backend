package Farm.Team4.FindOwnv2.repository;

import Farm.Team4.FindOwnv2.domain.community.board.Post;
import Farm.Team4.FindOwnv2.domain.community.board.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findAllByTagOrderByCreatedAtDesc(Tag tag);
    List<Post> findAllByTitleContainingOrderByCreatedAtDesc(String title);
}
