package Farm.Team4.FindOwnv2.service.community.comment;

import Farm.Team4.FindOwnv2.domain.community.board.Comment;
import Farm.Team4.FindOwnv2.domain.community.board.Post;
import Farm.Team4.FindOwnv2.domain.platform.Member;
import Farm.Team4.FindOwnv2.dto.community.comment.request.CreateCommentDTO;
import Farm.Team4.FindOwnv2.dto.community.comment.request.UpdateCommentDTO;
import Farm.Team4.FindOwnv2.exception.CustomErrorCode;
import Farm.Team4.FindOwnv2.exception.FindOwnException;
import Farm.Team4.FindOwnv2.repository.CommentRepository;
import Farm.Team4.FindOwnv2.service.community.post.PostService;
import Farm.Team4.FindOwnv2.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final PostService postService;
    @Transactional
    public void createComment(CreateCommentDTO createCommentDTO){
        Member commentWriter = getMember();
        Post findPost = postService.findById(createCommentDTO.getPostId());
        commentRepository.save(new Comment(commentWriter, findPost, createCommentDTO.getContent()));
        log.info("댓글 저장 완료");
    }
    public Comment findById(Long commentId){
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_FOUND_COMMENT));
    }
    @Transactional
    public void updateComment(UpdateCommentDTO updateCommentDTO){
        Comment findComment = findById(updateCommentDTO.getCommentId());
        log.info("댓글 정보 가져오기 성공");
        if (!findComment.getWriter().equals(getMember()))
            throw new FindOwnException(CustomErrorCode.NOT_MATCH_WRITER);
        findComment.update(updateCommentDTO.getContent());
        log.info("댓글 수정 완료");
    }
    @Transactional
    public void deleteComment(Long commentId){
        Comment findComment = findById(commentId);
        if (!findComment.getWriter().equals(getMember()))
            throw new FindOwnException(CustomErrorCode.NOT_MATCH_WRITER);
        commentRepository.delete(findComment);
        log.info("댓글 삭제 완료");
    }
    private Member getMember(){
        return memberService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
