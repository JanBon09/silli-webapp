package org.silli.sillibackend.repositories;

import org.silli.sillibackend.models.Post;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

    @Modifying
    @Query("INSERT INTO post (content, createdat, account_id) VALUES (:content, :createdAt, :accountId)")
    void save(String content, LocalDateTime createdAt, Integer accountId);
}
