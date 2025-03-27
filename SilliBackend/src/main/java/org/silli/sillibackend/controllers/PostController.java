package org.silli.sillibackend.controllers;

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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PostController.class);
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
    public ResponseEntity<Object> createPost(Authentication authentication, @RequestBody PostDto postDto) {
        postService.persist(authentication, postDto);

        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePost(Authentication authentication, @RequestBody PostDto postDto) {
        try{
            postService.delete(authentication, postDto);
        } catch(AuthorizationServiceException e){
            return ResponseEntity.status(401).build();
        }


        return ResponseEntity.status(200).build();
    }
}
