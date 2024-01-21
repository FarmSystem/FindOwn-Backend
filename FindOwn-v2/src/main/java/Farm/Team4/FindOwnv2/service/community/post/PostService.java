package Farm.Team4.FindOwnv2.service.community.post;

import Farm.Team4.FindOwnv2.domain.community.board.Post;
import Farm.Team4.FindOwnv2.domain.community.board.Tag;
import Farm.Team4.FindOwnv2.domain.platform.Member;
import Farm.Team4.FindOwnv2.dto.community.comment.response.ShowCommentDTO;
import Farm.Team4.FindOwnv2.dto.community.post.request.CreatePostDTO;
import Farm.Team4.FindOwnv2.dto.community.post.request.UpdatePostDTO;
import Farm.Team4.FindOwnv2.dto.community.post.response.ShowPostDetailDTO;
import Farm.Team4.FindOwnv2.dto.community.post.response.ShowPostSimpleDTO;
import Farm.Team4.FindOwnv2.exception.CustomErrorCode;
import Farm.Team4.FindOwnv2.exception.FindOwnException;
import Farm.Team4.FindOwnv2.repository.PostRepository;
import Farm.Team4.FindOwnv2.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    @Transactional
    public void createPost(CreatePostDTO createPostDTO){
        Post newPost = createPostDTO.toPost(getMember());
        postRepository.save(newPost);
        log.info("게시글 저장 성공");
    }
    public Post findById(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_POST));
    }
    public ShowPostDetailDTO showPostDetail(Long postId){
        Post destPost = findById(postId);
        log.info("게시글 가져오기 성공");
        increaseView(destPost);
        log.info("조회수 증가 성공");
        return new ShowPostDetailDTO(
                destPost.getId(),
                destPost.getTitle(),
                destPost.getWriter().getUsername(),
                destPost.getTag().toString(),
                destPost.getContent(),
                destPost.getCreatedAt(),
                destPost.getViewCnt(),
                destPost.getComments().stream()
                        .map(comment -> new ShowCommentDTO(comment.getId(), comment.getWriter().getUsername(), comment.getContent(), comment.getCreatedAt()))
                        .toList()
        );
    }
    public List<ShowPostSimpleDTO> showAllPostSimple(){
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(post -> new ShowPostSimpleDTO(
                        post.getId(),
                        post.getTitle(),
                        post.getWriter().getUsername(),
                        post.getTag().name(),
                        post.getCreatedAt(),
                        post.getComments().size(),
                        post.getViewCnt()))
                .toList();
    }
    public List<ShowPostSimpleDTO> showTagPostSimple(String tagName) {
        return postRepository.findAllByTagOrderByCreatedAtDesc(Tag.valueOf(tagName)).stream()
                .map(post -> new ShowPostSimpleDTO(
                        post.getId(),
                        post.getTitle(),
                        post.getWriter().getUsername(),
                        post.getTag().name(),
                        post.getCreatedAt(),
                        post.getComments().size(),
                        post.getViewCnt()))
                .toList();
    }
    public List<ShowPostSimpleDTO> showSearchResults(String title){
        return postRepository.findAllByTitleContainingOrderByCreatedAtDesc(title).stream()
                .map(post -> new ShowPostSimpleDTO(
                        post.getId(),
                        post.getTitle(),
                        post.getWriter().getUsername(),
                        post.getTag().name(),
                        post.getCreatedAt(),
                        post.getComments().size(),
                        post.getViewCnt()))
                .toList();
    }

    @Transactional
    public void updatePost(UpdatePostDTO updatePostDTO){
        Post findPost = findById(updatePostDTO.getPostId());
        findPost.update(updatePostDTO);
        log.info("게시글 내용 수정 완료");
    }
    @Transactional
    public void increaseView(Post post){
        post.increaseView();
        postRepository.save(post);
    }
    @Transactional
    public void deletePost(Long postId){
        Post findPost = findById(postId);
        if (!findPost.getWriter().equals(getMember()))
            throw new FindOwnException(CustomErrorCode.NOT_MATCH_WRITER);
        postRepository.delete(findPost);
        log.info("게시글 삭제 완료");
    }
    private Member getMember(){
        return memberService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
