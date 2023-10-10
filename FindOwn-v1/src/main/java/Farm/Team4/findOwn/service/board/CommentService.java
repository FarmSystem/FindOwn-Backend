package Farm.Team4.findOwn.service.board;

import Farm.Team4.findOwn.domain.board.Comment;
import Farm.Team4.findOwn.domain.board.post.Post;
import Farm.Team4.findOwn.domain.member.Member;
import Farm.Team4.findOwn.dto.board.comment.request.SaveCommentRequest;
import Farm.Team4.findOwn.dto.board.comment.request.UpdateCommentRequest;
import Farm.Team4.findOwn.dto.board.comment.response.SaveCommentResponse;
import Farm.Team4.findOwn.dto.board.comment.response.UpdateCommentResponse;
import Farm.Team4.findOwn.exception.CustomErrorCode;
import Farm.Team4.findOwn.exception.FindOwnException;
import Farm.Team4.findOwn.repository.board.CommentRepository;
import Farm.Team4.findOwn.service.board.post.PostService;
import Farm.Team4.findOwn.service.member.information.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final MemberService memberService;
    @Transactional
    public SaveCommentResponse saveComment(SaveCommentRequest request){
        Post destPost = postService.findById(request.getPostId());
        Member writer = memberService.findByNickname(request.getNickname());
        Comment savedComment = commentRepository.save(new Comment(request.getContent(), writer, destPost));
        return new SaveCommentResponse(savedComment.getId(), writer.getNickname(), savedComment.getContent(), savedComment.getCreatedAt());
    }
    public Comment findById(Long commentId){
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new FindOwnException(CustomErrorCode.NOT_MATCH_COMMENT));
    }
    @Transactional
    public UpdateCommentResponse updateComment(UpdateCommentRequest request){
        Comment findComment = findById(request.getCommentId());
        if (!findComment.getWriter().getNickname().equals(request.getNickname()))
            throw new FindOwnException(CustomErrorCode.NOT_MATCH_MEMBER);
        findComment.updateComment(request.getContent());
        return new UpdateCommentResponse(findComment.getId(), findComment.getWriter().getNickname(), findComment.getContent(), findComment.getCreatedAt());
    }
    @Transactional
    public void deleteComment(Long commentId){
        Comment findComment = findById(commentId);
        commentRepository.delete(findComment);
    }
}
