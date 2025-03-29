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
    private final JwtDecodingService jwtDecodingService;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, JwtDecodingService jwtDecodingService,
                          EntityManipulationAuthService entityManipulationAuthService, PostService postService, PostRepository postRepository) {

        this.commentRepository = commentRepository;
        this.jwtDecodingService = jwtDecodingService;
        this.entityManipulationAuthService = entityManipulationAuthService;
        this.postService = postService;
        this.postRepository = postRepository;
    }

    public Page<Comment> findPageSortedBy(Pageable pageable){
        return commentRepository.findAll(pageable);
    }

    public void persist(Authentication authentication, CommentDto commentDto) {
        String username = jwtDecodingService.decodeUsernameFromJWT(authentication);

        commentRepository.save(commentDto.getContent(), LocalDateTime.now(), username, commentDto.getPostId());
    }

    public void deleteAsCreator(Authentication authentication, int commentId) throws AuthorizationServiceException {
        if(!entityManipulationAuthService.checkAuthByEntity(authentication, commentId, commentRepository)){
            throw new AuthorizationServiceException("Unauthorized");
        }

        commentRepository.delete(commentId);
    }

    public void deleteAsPostCreator(Authentication authentication, int commentId)
            throws AuthorizationServiceException {
        int postCreatorId = postService.getAccountIdById(commentId);

        if(!entityManipulationAuthService.checkAuthByEntity(authentication, postCreatorId, postRepository)){
            throw new AuthorizationServiceException("Unauthorized");
        }

        commentRepository.delete(commentId);
    }
}
