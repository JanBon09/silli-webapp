package org.silli.sillibackend.repositories;

import org.silli.sillibackend.models.Group;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;

public interface GroupRepository extends PagingAndSortingRepository<Group, Integer> {
    @Modifying
    @Query("INSERT INTO social_group (name, createdat, accessibility, account_id) VALUES " +
            "(:name, :createdAt, :accessibility, (SELECT id FROM account WHERE username LIKE :username))")
    void save(String name, LocalDateTime createdAt, String accessibility, String username);

    @Modifying
    @Query("UPDATE social_group SET name = :newName WHERE name LIKE :name")
    void updateName(String name, String newName);

    @Modifying
    @Query("DELETE FROM social_group WHERE id = :groupId")
    void delete(int groupId);
}
