package app;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    PostRestClient postRestClient;

    public PostController() {
        this.postRestClient = new PostRestClient();
    }

    @GetMapping(path = "/api/posts")
    public List<PostDto> getPosts() {

        return postRestClient.clientGetPosts();
    }


    @PostMapping("/api/posts")
    public PostDto addPost(@RequestBody PostDto postDto) {

        return postRestClient.clientAddPost(postDto);
    }

    @PutMapping("/api/posts")
    public PostDto putPost(@RequestBody PostDto postDto, @RequestParam long id) {

        return postRestClient.clientPutPost(postDto, id);
    }

    @DeleteMapping("/api/posts")
    public PostDto deletePost(@RequestParam long id) {

        return postRestClient.clientDeletePost(id);
    }
}
