package org.silli.sillibackend.repositories;

import org.silli.sillibackend.models.Post;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface PostRepository extends CrudRepository<Post, Integer> {

    @Modifying
    @Query("INSERT INTO post (content, createdat, account_id) VALUES (:content, :createdat, (SELECT id FROM account WHERE username LIKE :username))")
    void save(String content, LocalDateTime createdat, String username);
}
