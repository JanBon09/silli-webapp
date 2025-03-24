package org.silli.sillibackend.repositories;

import org.silli.sillibackend.models.Account;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Query("SELECT * FROM accounts WHERE username LIKE :username")
    Account findByUsername(String username);

    @Modifying
    @Query("INSERT INTO accounts (username, password, enabled, authority) VALUES (:username, :password, :enabled, :authority)")
    void insert(String username, String password, int enabled, String authority);
}
