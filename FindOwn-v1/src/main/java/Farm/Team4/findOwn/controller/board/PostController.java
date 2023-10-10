package Farm.Team4.findOwn.controller.board;

import Farm.Team4.findOwn.domain.board.Tag;
import Farm.Team4.findOwn.domain.board.post.Post;
import Farm.Team4.findOwn.domain.board.post.PostWithTag;
import Farm.Team4.findOwn.dto.board.BoardPageDTO;
import Farm.Team4.findOwn.dto.board.post.request.SavePostRequest;
import Farm.Team4.findOwn.dto.board.post.request.UpdatePostRequest;
import Farm.Team4.findOwn.dto.board.post.response.DetailPostDTO;
import Farm.Team4.findOwn.dto.board.post.response.SavePostResponse;
import Farm.Team4.findOwn.dto.board.post.response.UpdatePostResponse;
import Farm.Team4.findOwn.service.board.TagService;
import Farm.Team4.findOwn.service.board.post.PostService;
import Farm.Team4.findOwn.service.board.post.PostWithTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public SavePostResponse savePost(@RequestBody SavePostRequest request) {
        return postService.savePost(request);
    }

    @GetMapping("/board")
    public BoardPageDTO showBoardPage(@RequestParam int pageNum) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, 5, Sort.by("createdAt").descending());
        return new BoardPageDTO(postService.countPosts(), postService.startPagingBoard(pageRequest));
    }

    @GetMapping("/post/{postId}")
    public DetailPostDTO showPostDetail(@PathVariable Long postId) {
        return postService.findDetailPost(postId);
    }
    @PatchMapping("/post/{postId}")
    public UpdatePostResponse updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest request){
        return postService.updatePost(postId, request);
    }
    @DeleteMapping("/post/{postId}")
    public String deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return "delete post complete";
    }
}
