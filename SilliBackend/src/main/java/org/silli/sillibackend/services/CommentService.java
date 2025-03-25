package org.silli.sillibackend.services;

import org.silli.sillibackend.models.Comment;
import org.silli.sillibackend.models.CommentDto;
import org.silli.sillibackend.repositories.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Page<Comment> findPageSortedBy(Pageable pageable){
        return commentRepository.findAll(pageable);
    }

    public void persist(CommentDto commentDto, String username, int postId) {
        commentRepository.save(commentDto.getContent(), LocalDateTime.now(), username, postId);
    }
}
