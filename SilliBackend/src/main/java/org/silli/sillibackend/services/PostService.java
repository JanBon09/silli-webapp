package org.silli.sillibackend.services;

import org.silli.sillibackend.models.Post;
import org.silli.sillibackend.models.PostDto;
import org.silli.sillibackend.repositories.PostRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final EntityManipulationAuthService entityManipulationAuthService;

    public PostService(PostRepository postRepository, EntityManipulationAuthService entityManipulationAuthService) {
        this.postRepository = postRepository;
        this.entityManipulationAuthService = entityManipulationAuthService;
    }

    public Page<Post> findPageSortedBy(Pageable pageable) {

        return postRepository.findAll(pageable);
    }

    public int getAccountIdById(int postId){

        return postRepository.findAccountId(postId);
    }

    public void persist(String subject, PostDto postDto) {
        postRepository.save(postDto.getContent(), LocalDateTime.now(), subject);
    }


    public void delete(String subject, int postId) throws AuthorizationServiceException {
        if(!entityManipulationAuthService.authBySubjectAndEntity(subject, postId, postRepository)){
            throw new AuthorizationServiceException("Unathorized");
        }

        postRepository.delete(postId);
    }

    public PostDto getPostDtoById(int postId) throws ChangeSetPersister.NotFoundException {
        PostDto postDto = postRepository.findPostInDisplayForm(postId);
        if(postDto == null){
            throw new ChangeSetPersister.NotFoundException();
        }

        return postDto;

    }
}
