package Farm.Team4.FindOwnv2.controller;

import Farm.Team4.FindOwnv2.dto.community.post.request.CreatePostDTO;
import Farm.Team4.FindOwnv2.service.community.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping("/community/post/write")
    public void createPost(@RequestBody CreatePostDTO createPostDTO){
        postService.createPost(createPostDTO);
    }
}
