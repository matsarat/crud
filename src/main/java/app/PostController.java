package app;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
public class PostController {

    @GetMapping("/")
    public String elo() {
        return "ELO";
    }


    @GetMapping(path = "/api/posts")
    public void getPosts() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<PostDto[]> responseEntity = restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/posts",
                HttpMethod.GET,
                null,
                PostDto[].class);


        Stream.of(Objects.requireNonNull(responseEntity.getBody())).forEach(System.out::println);
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
