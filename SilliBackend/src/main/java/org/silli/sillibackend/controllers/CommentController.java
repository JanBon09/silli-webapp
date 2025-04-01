package org.silli.sillibackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.silli.sillibackend.models.Comment;
import org.silli.sillibackend.models.CommentDto;
import org.silli.sillibackend.services.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
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
    public ResponseEntity<Object> createComment(HttpServletRequest request, @RequestBody CommentDto commentDto){
        commentService.persist((String) request.getAttribute("Subject"), commentDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/creator")
    public ResponseEntity<Object> deleteCommentAsCreator(HttpServletRequest request, @RequestParam int commentId){
        try{
            commentService.deleteAsCreator((String) request.getAttribute("Subject"), commentId);
        } catch(AuthorizationServiceException e){
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/post-creator")
    public ResponseEntity<Object> deleteCommentAsPostCreator(HttpServletRequest request, @RequestParam int commentId){
        try{
            commentService.deleteAsPostCreator((String) request.getAttribute("Subject"), commentId);
        } catch(AuthorizationServiceException e){
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok().build();
    }
}
