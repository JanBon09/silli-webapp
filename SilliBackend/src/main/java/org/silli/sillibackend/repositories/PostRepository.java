package org.silli.sillibackend.repositories;

import org.silli.sillibackend.models.Post;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer>, EntityRepository {

    @Modifying
    @Query("INSERT INTO post (content, createdat, account_id) VALUES (:content, :createdat, (SELECT id FROM account WHERE username LIKE :username))")
    void save(String content, LocalDateTime createdat, String username);

    @Modifying
    @Query("DELETE FROM post WHERE id = :postId")
    void delete(int postId);

    @Query("SELECT account_id FROM post WHERE id = :postId")
    int findAccountId(int postId);

    @Query("SELECT account_id FROM post WHERE id = :entityId")
    int findOwner(int entityId);
}
