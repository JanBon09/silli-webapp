package org.silli.sillibackend.controllers;

import org.silli.sillibackend.models.PostDto;
import org.silli.sillibackend.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getAllPosts() {
        return "All posts";
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createPost(Authentication authentication, @RequestBody PostDto postDto) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String username = jwt.getSubject();
        Logger logger = Logger.getLogger(this.getClass().getName());
        logger.info(username);

        postService.persist(postDto, username);

        return ResponseEntity.status(200).build();
    }
}
