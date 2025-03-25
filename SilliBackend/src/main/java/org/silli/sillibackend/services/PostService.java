package org.silli.sillibackend.services;

import org.silli.sillibackend.models.Post;
import org.silli.sillibackend.models.PostDto;
import org.silli.sillibackend.repositories.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Page<Post> findPageSortedBy(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public void persist(PostDto postDto, String username){
        postRepository.save(postDto.getContent(), LocalDateTime.now(), username);
    }
}
