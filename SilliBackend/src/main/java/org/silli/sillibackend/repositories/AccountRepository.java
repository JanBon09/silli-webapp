package org.silli.sillibackend.repositories;

import org.silli.sillibackend.models.Account;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer>, EntityRepository {

    @Query("SELECT * FROM account WHERE username LIKE :username")
    Account findByUsername(String username);

    @Query("SELECT username FROM account WHERE id = :id")
    String findPublicInfoByID(int id);

    @Query("SELECT id FROM account WHERE username LIKE :username")
    Integer findIdByUsername(String username);

    @Query("SELECT account_id FROM account WHERE id = :entityId")
    int findOwner(int entityId);
}
