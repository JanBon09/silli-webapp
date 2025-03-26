package org.silli.sillibackend.controllers;

import org.silli.sillibackend.models.Comment;
import org.silli.sillibackend.models.CommentDto;
import org.silli.sillibackend.services.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public Page<Comment> getCommentsInPages(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam Boolean ascending
    ){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return  commentService.findPageSortedBy(pageable);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createComment(Authentication authentication, @RequestBody CommentDto commentDto){
        commentService.persist(authentication, commentDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/creator")
    public ResponseEntity<Object> deleteCommentAsCreator(Authentication authentication, @RequestBody CommentDto commentDto){
        try{
            commentService.deleteAsCreator(authentication, commentDto);
        } catch(AuthorizationServiceException e){
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/post-creator")
    public ResponseEntity<Object> deleteCommentAsPostCreator(Authentication authentication, @RequestBody CommentDto commentDto){
        try{
            commentService.deleteAsPostCreator(authentication, commentDto);
        } catch(AuthorizationServiceException e){
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok().build();
    }
}
