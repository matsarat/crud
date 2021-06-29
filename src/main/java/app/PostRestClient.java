package app;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public class PostRestClient {

    private final RestTemplate restTemplate;

    public PostRestClient() {
        this.restTemplate = new RestTemplate();
    }

    private HttpEntity<PostDto> createHttpEntity(PostDto postDto) {

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_TYPE, "application/json");

        return new HttpEntity<>(postDto, header);
    }

    public List<PostDto> clientGetPosts() {

        ResponseEntity<List<PostDto>> responseEntity = restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/posts",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PostDto>>() {
                });

        return responseEntity.getBody();
    }

    public PostDto clientAddPost(PostDto postDto) {

        ResponseEntity<PostDto> responseEntity = restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/posts/",
                HttpMethod.POST,
                createHttpEntity(postDto),
                PostDto.class);

        return responseEntity.getBody();

    }

    public PostDto clientPutPost(PostDto postDto, long id) {

        ResponseEntity<PostDto> responseEntity = restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/posts/{id}",
                HttpMethod.PUT,
                createHttpEntity(postDto),
                PostDto.class,
                Map.of("id", id)
        );

        return responseEntity.getBody();
    }

    public PostDto clientDeletePost(long id) {

        ResponseEntity<PostDto> responseEntity = restTemplate.exchange(
                "https://jsonplaceholder.typicode.com/posts/{id}",
                HttpMethod.DELETE,
                null,
                PostDto.class,
                Map.of("id", id));

        return responseEntity.getBody();
    }
}
