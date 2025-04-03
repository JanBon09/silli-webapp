package org.silli.sillibackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.silli.sillibackend.models.Post;
import org.silli.sillibackend.models.PostDto;
import org.silli.sillibackend.repositories.PostRepository;
import org.silli.sillibackend.services.PostService;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService, PostRepository postRepository) {
        this.postService = postService;
    }

    @GetMapping
    public Page<Post> getPostsInPages(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam Boolean ascending
    ){
            Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
            Pageable pageable = PageRequest.of(page, size, sort);

            return postService.findPageSortedBy(pageable);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createPost(HttpServletRequest request, @RequestBody PostDto postDto) {
        postService.persist((String) request.getAttribute("Subject"), postDto);

        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePost(HttpServletRequest request, @RequestParam int postId) {
        try{
            postService.delete((String) request.getAttribute("Subject"), postId);
        } catch(AuthorizationServiceException e){
            return ResponseEntity.status(401).build();
        }


        return ResponseEntity.status(200).build();
    }

    @GetMapping("specific")
    public ResponseEntity<Post> getPostById(HttpServletRequest request, @RequestParam int postId) {
        Post post = null;
        try{
            post = postService.getPostById((String)request.getAttribute("Subject"), postId);
        } catch(AuthorizationServiceException e){
            return ResponseEntity.status(401).build();
        }

        if (post == null){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(post);
    }
}
