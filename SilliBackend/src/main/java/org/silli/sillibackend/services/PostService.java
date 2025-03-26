package org.silli.sillibackend.services;

import org.silli.sillibackend.models.Post;
import org.silli.sillibackend.models.PostDto;
import org.silli.sillibackend.repositories.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final EntityManipulationAuthService entityManipulationAuthService;
    private final JwtDecodingService jwtDecodingService;

    public PostService(PostRepository postRepository, EntityManipulationAuthService entityManipulationAuthService,
                       JwtDecodingService jwtDecodingService) {
        this.postRepository = postRepository;
        this.entityManipulationAuthService = entityManipulationAuthService;
        this.jwtDecodingService = jwtDecodingService;
    }

    public Page<Post> findPageSortedBy(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public int getAccountIdById(int postId){
        return postRepository.findAccountId(postId);
    }

    public void persist(Authentication authentication, PostDto postDto) {
        String username = jwtDecodingService.decodeUsernameFromJWT(authentication);

        postRepository.save(postDto.getContent(), LocalDateTime.now(), username);
    }

    public void delete(Authentication authentication, PostDto postDto) throws AuthorizationServiceException {
        if(!entityManipulationAuthService.checkAuthByEntity(authentication, postDto)){
            throw new AuthorizationServiceException("Unathorized");
        }

        postRepository.delete(postDto.getId());
    }
}
