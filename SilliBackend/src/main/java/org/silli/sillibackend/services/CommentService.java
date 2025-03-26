package org.silli.sillibackend.services;

import org.silli.sillibackend.models.Comment;
import org.silli.sillibackend.models.CommentDto;
import org.silli.sillibackend.repositories.CommentRepository;
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

    public CommentService(CommentRepository commentRepository, JwtDecodingService jwtDecodingService,
                          EntityManipulationAuthService entityManipulationAuthService, PostService postService) {

        this.commentRepository = commentRepository;
        this.jwtDecodingService = jwtDecodingService;
        this.entityManipulationAuthService = entityManipulationAuthService;
        this.postService = postService;
    }

    public Page<Comment> findPageSortedBy(Pageable pageable){
        return commentRepository.findAll(pageable);
    }

    public void persist(Authentication authentication, CommentDto commentDto) {
        String username = jwtDecodingService.decodeUsernameFromJWT(authentication);

        commentRepository.save(commentDto.getContent(), LocalDateTime.now(), username, commentDto.getPostId());
    }

    public void deleteAsCreator(Authentication authentication, CommentDto commentDto) throws AuthorizationServiceException {
        if(!entityManipulationAuthService.checkAuthByEntity(authentication, commentDto)){
            throw new AuthorizationServiceException("Unauthorized");
        }

        commentRepository.delete(commentDto.getId());
    }

    public void deleteAsPostCreator(Authentication authentication, CommentDto commentDto)
            throws AuthorizationServiceException {
        int postCreatorId = postService.getAccountIdById(commentDto.getPostId());

        if(!entityManipulationAuthService.checkAuthById(authentication, postCreatorId)){
            throw new AuthorizationServiceException("Unauthorized");
        }

        commentRepository.delete(commentDto.getId());
    }
}
