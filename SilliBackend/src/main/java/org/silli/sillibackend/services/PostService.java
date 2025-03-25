package org.silli.sillibackend.services;

import org.silli.sillibackend.models.PostDto;
import org.silli.sillibackend.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void persist(PostDto postDto, String username){
        postRepository.save(postDto.getContent(), LocalDateTime.now(), username);
    }
}
