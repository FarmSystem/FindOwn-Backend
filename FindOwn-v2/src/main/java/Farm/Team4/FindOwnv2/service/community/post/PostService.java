package Farm.Team4.FindOwnv2.service.community.post;

import Farm.Team4.FindOwnv2.domain.community.board.Post;
import Farm.Team4.FindOwnv2.domain.platform.Member;
import Farm.Team4.FindOwnv2.dto.community.post.request.CreatePostDTO;
import Farm.Team4.FindOwnv2.repository.PostRepository;
import Farm.Team4.FindOwnv2.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    @Transactional
    public void createPost(CreatePostDTO createPostDTO){
        log.info("진입성공");
        Post newPost = createPostDTO.toPost(getMember());
        postRepository.save(newPost);
        log.info("저장 성공");
    }
    private Member getMember(){
        return memberService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
