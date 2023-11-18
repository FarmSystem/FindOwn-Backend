package Farm.Team4.FindOwnv2.repository;

import Farm.Team4.FindOwnv2.domain.community.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long postId);
}
