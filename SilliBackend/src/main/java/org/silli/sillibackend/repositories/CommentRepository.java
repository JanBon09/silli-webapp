package org.silli.sillibackend.repositories;

import org.silli.sillibackend.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer>, EntityRepository {
    @Modifying
    @Query("INSERT INTO comment (content, createdat, account_id, post_id) VALUES " +
            "(:content, :createdAt, (SELECT id FROM account WHERE username LIKE :username), :postId)")
    void save(String content, LocalDateTime createdAt, String username, int postId);

    @Modifying
    @Query("DELETE FROM comment WHERE id = :commentId")
    void delete(int commentId);

    @Query("SELECT account_id FROM comment WHERE id = :entityId")
    int findOwner(int entityId);

    Page<Comment> findAllByPostId(int postId, Pageable pageable);
}
