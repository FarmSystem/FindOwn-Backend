package Farm.Team4.findOwn.repository.board;

import Farm.Team4.findOwn.domain.board.post.Post;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);
    Page<Post> findAll(Pageable pageable);
}
