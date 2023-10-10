package Farm.Team4.findOwn.repository.board;

import Farm.Team4.findOwn.domain.board.Tag;
import Farm.Team4.findOwn.domain.board.post.Post;
import Farm.Team4.findOwn.domain.board.post.PostWithTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostWithTagRepository extends JpaRepository<PostWithTag, Long> {
    Long countByTag(Tag tag);
    List<PostWithTag> findPostWithTagsByPost(Post post);
    Optional<PostWithTag> findByPostAndTag(Post post, Tag tag);
    void deleteById(Long id);
}
