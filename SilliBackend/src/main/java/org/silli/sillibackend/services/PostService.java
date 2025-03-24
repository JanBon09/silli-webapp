package org.silli.sillibackend.services;

import org.silli.sillibackend.models.Account;
import org.silli.sillibackend.models.AccountDto;
import org.silli.sillibackend.models.Post;
import org.silli.sillibackend.repositories.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void getAll(){

    }

    public void getByUsername(String username){}

    public void getByPostId(Integer postId){}

    public void persist(Post post, AccountDto accountDto){
        postRepository.save(post.getContent(), LocalDateTime.now(), 0);
    }
}
