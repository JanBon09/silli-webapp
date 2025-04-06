package org.silli.sillibackend.services;

import org.silli.sillibackend.models.Comment;
import org.silli.sillibackend.models.CommentDto;
import org.silli.sillibackend.repositories.CommentRepository;
import org.silli.sillibackend.repositories.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final EntityManipulationAuthService entityManipulationAuthService;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, EntityManipulationAuthService entityManipulationAuthService,
                          PostService postService, PostRepository postRepository) {

        this.commentRepository = commentRepository;
        this.entityManipulationAuthService = entityManipulationAuthService;
        this.postService = postService;
        this.postRepository = postRepository;
    }

    public Page<Comment> findPageSortedBy(Pageable pageable){
        return commentRepository.findAll(pageable);
    }

    public void persist(String subject, CommentDto commentDto) {
        commentRepository.save(commentDto.getContent(), LocalDateTime.now(), subject, commentDto.getPostId());
    }

    public void deleteAsCreator(String subject, int commentId) throws AuthorizationServiceException {
        if(!entityManipulationAuthService.authBySubjectAndEntity(subject, commentId, commentRepository)){
            throw new AuthorizationServiceException("Unauthorized");
        }

        commentRepository.delete(commentId);
    }

    public void deleteAsPostCreator(String subject, int commentId)
            throws AuthorizationServiceException {
        int postCreatorId = postService.getAccountIdById(commentId);

        if(!entityManipulationAuthService.authBySubjectAndEntity(subject, postCreatorId, postRepository)){
            throw new AuthorizationServiceException("Unauthorized");
        }

        commentRepository.delete(commentId);
    }

    public Page<Comment> getAllByPostId(int postId, Pageable pageable) {
        return commentRepository.findAllByPostId(postId, pageable);
    }
}
