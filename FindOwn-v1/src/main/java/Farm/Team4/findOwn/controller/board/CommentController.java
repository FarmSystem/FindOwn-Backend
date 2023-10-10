package Farm.Team4.findOwn.controller.board;

import Farm.Team4.findOwn.dto.board.comment.request.SaveCommentRequest;
import Farm.Team4.findOwn.dto.board.comment.request.UpdateCommentRequest;
import Farm.Team4.findOwn.dto.board.comment.response.SaveCommentResponse;
import Farm.Team4.findOwn.dto.board.comment.response.UpdateCommentResponse;
import Farm.Team4.findOwn.service.board.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/comment")
    public SaveCommentResponse saveComment(@RequestBody SaveCommentRequest request){
        return commentService.saveComment(request);
    }
    @PatchMapping("/comment/{commentId}")
    public UpdateCommentResponse updateComment(@RequestBody UpdateCommentRequest request){
        return commentService.updateComment(request);
    }
    @DeleteMapping("/comment/{commentId}")
    public String deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return "delete comment complete";
    }

}
