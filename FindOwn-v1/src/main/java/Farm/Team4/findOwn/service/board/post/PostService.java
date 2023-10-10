package Farm.Team4.findOwn.service.board.post;

import Farm.Team4.findOwn.domain.board.Tag;
import Farm.Team4.findOwn.domain.board.post.Post;
import Farm.Team4.findOwn.domain.board.post.PostWithTag;
import Farm.Team4.findOwn.dto.board.comment.response.CommentDTO;
import Farm.Team4.findOwn.dto.board.post.request.SavePostRequest;
import Farm.Team4.findOwn.dto.board.post.request.UpdatePostRequest;
import Farm.Team4.findOwn.dto.board.post.response.DetailPostDTO;
import Farm.Team4.findOwn.dto.board.post.response.SavePostResponse;
import Farm.Team4.findOwn.dto.board.post.response.SimplePostDTO;
import Farm.Team4.findOwn.dto.board.post.response.UpdatePostResponse;
import Farm.Team4.findOwn.exception.CustomErrorCode;
import Farm.Team4.findOwn.exception.FindOwnException;
import Farm.Team4.findOwn.repository.board.PostRepository;
import Farm.Team4.findOwn.repository.board.PostWithTagRepository;
import Farm.Team4.findOwn.service.board.TagService;
import Farm.Team4.findOwn.service.member.information.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostWithTagRepository postWithTagRepository;
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final TagService tagService;
    private final PostWithTagService postWithTagService;
    @Transactional
    public SavePostResponse savePost(SavePostRequest request){
        Post savedPost = postRepository.save(request.changeToPost(memberService.findById(request.getWriterId())));
        log.info("게시글 저장 완료");
        tagService.saveNewTag(request.getTagNames()); // 새로운 태그 저장 & 기존 태그는 저장은 X
        postWithTagService.saveAssociationsInSave(savedPost, request.getTagNames());

        return new SavePostResponse(savedPost.getId(), savedPost.getTitle(), savedPost.getContent(), savedPost.getCreatedAt());
    }
    public Post findById(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_MATCH_POST));
    }
    public Long countPosts(){
        return postRepository.count();
    }
    public List<SimplePostDTO> startPagingBoard(PageRequest pageRequest){
        return postRepository.findAll(pageRequest).stream()
                .map(post -> new SimplePostDTO(
                        post.getId(),
                        post.getMember().getNickname(),
                        post.getTitle(),
                        postWithTagService.findAssociations(post).stream()
                                .map(association -> association.getTag().getName())
                                .toList(),
                        post.getCreatedAt()
                )).toList();
    }
    public DetailPostDTO findDetailPost(Long postId){
        Post findPost = findById(postId);
        return new DetailPostDTO(
                findPost.getMember().getNickname(),
                findPost.getTitle(),
                findPost.getContent(),
                findPost.getCreatedAt(),
                postWithTagService.findAssociations(findPost).stream()
                                .map(association -> association.getTag().getName())
                                .toList(),
                findPost.getComments().stream()
                        .map(comment -> new CommentDTO(comment.getId(), comment.getWriter().getNickname(), comment.getContent(), comment.getCreatedAt()))
                        .toList()
        );
    }
    @Transactional
    public UpdatePostResponse updatePost(Long postId, UpdatePostRequest request){
        Post updatedPost = findById(postId).updatePost(request);
        log.info("게시글 내용 업데이트 완료");
        tagService.saveNewTag(request.getTagNames());
        log.info("새로 저장해야하는 태그 저장 완료");

        List<Tag> originTags = postWithTagService.findAssociations(updatedPost).stream()
                .map(association -> association.getTag()).toList();
        List<Tag> newTags = request.getTagNames().stream()
                .map(tagName -> tagService.findByTagName(tagName)).toList();
        log.info("태그 분리 목록 생성 완료");

        postWithTagService.editAssociationInUpdate(updatedPost, originTags, newTags);
        log.info("태그 & 게시글 연관관계 재정의 완료");

        return new UpdatePostResponse(updatedPost.getId(), updatedPost.getTitle(), updatedPost.getContent(), updatedPost.getCreatedAt());

    }
    @Transactional
    public void deletePost(Long postId) {
        Post findPost = findById(postId);
        postRepository.delete(findPost);
        log.info("게시글 삭제 완료");
    }
}
