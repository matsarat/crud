package app;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class PostController {


    @GetMapping(path = "/api/posts")
    public List<PostDto> getPosts() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<PostDto>> responseEntity = restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/posts",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PostDto>>() {
                });

        return responseEntity.getBody();
    }


    @PostMapping("/api/posts")
    public void addPost(@RequestBody PostDto postDto) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpEntity<PostDto> httpEntity = new HttpEntity<>(postDto, httpHeader);

        ResponseEntity<PostDto> responseEntity = restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/posts",
                HttpMethod.POST,
                httpEntity,
                PostDto.class);

        System.out.println(responseEntity.getBody());
    }

    @PatchMapping("/api/posts")
    public void patchPost(@RequestBody PostDto postDto) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpEntity<PostDto> httpEntity = new HttpEntity<>(postDto, httpHeader);

        ResponseEntity<PostDto> responseEntity = restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/posts/",
                HttpMethod.PATCH,
                httpEntity,
                PostDto.class);

        System.out.println(responseEntity.getBody());
    }

    @DeleteMapping("/api/posts")
    public void deletePost(@RequestParam long id) {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PostDto> responseEntity = restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/posts/{id}",
                HttpMethod.DELETE,
                null,
                PostDto.class,
                Map.of("id", id));

        System.out.println(responseEntity.getBody());

    }
}
