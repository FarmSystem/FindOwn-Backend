package Farm.Team4.FindOwnv2.controller;

import Farm.Team4.FindOwnv2.dto.community.comment.request.CreateCommentDTO;
import Farm.Team4.FindOwnv2.dto.community.comment.request.UpdateCommentDTO;
import Farm.Team4.FindOwnv2.service.community.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v2/users/community/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/write")
    public void createComment(@RequestBody CreateCommentDTO createCommentDTO){
        commentService.createComment(createCommentDTO);
    }
    @PatchMapping("/edit")
    public void updateComment(@RequestBody UpdateCommentDTO updateCommentDTO){
        commentService.updateComment(updateCommentDTO);
    }
    @DeleteMapping("/delete")
    public void deleteComment(@RequestParam Long id){
        commentService.deleteComment(id);
    }
}
