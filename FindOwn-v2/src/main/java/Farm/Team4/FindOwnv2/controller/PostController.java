package Farm.Team4.FindOwnv2.controller;

import Farm.Team4.FindOwnv2.dto.community.post.request.CreatePostDTO;
import Farm.Team4.FindOwnv2.dto.community.post.request.UpdatePostDTO;
import Farm.Team4.FindOwnv2.dto.community.post.response.ShowPostDetailDTO;
import Farm.Team4.FindOwnv2.dto.community.post.response.ShowPostSimpleDTO;
import Farm.Team4.FindOwnv2.service.community.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping("/community/post")
    public void createPost(@RequestBody CreatePostDTO createPostDTO){
        postService.createPost(createPostDTO);
    }
    @GetMapping("/community/post/")
    public ShowPostDetailDTO showPostDetail(@RequestParam Long id){
        return postService.showPostDetail(id);
    }
    @GetMapping("/community/post")
    public List<ShowPostSimpleDTO> showAllPostSimple(){
        return postService.showAllPostSimple();
    }
    @GetMapping("/community/post/{tagName}")
    public List<ShowPostSimpleDTO> showTagPostSimple(@PathVariable String tagName){
        return postService.showTagPostSimple(tagName);
    }
    @GetMapping("/community/post/search")
    public List<ShowPostSimpleDTO> showSearchResults(@RequestParam String title){
        return postService.showSearchResults(title);
    }
    @PatchMapping("/community/post")
    public void updatePost(@RequestBody UpdatePostDTO updatePostDTO){
        postService.updatePost(updatePostDTO);
    }
    @DeleteMapping("/community/post")
    public void deletePost(@RequestParam Long id){
        postService.deletePost(id);
    }
}
