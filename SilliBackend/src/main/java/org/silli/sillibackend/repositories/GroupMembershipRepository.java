package org.silli.sillibackend.repositories;

import org.silli.sillibackend.models.Account;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface GroupMembershipRepository extends CrudRepository<Account, Long> {
    @Modifying
    @Query("DO $$ " +
            "DECLARE acc_id INTEGER; " +
            "BEGIN " +
            "SELECT (SELECT id FROM account WHERE username LIKE :username) INTO acc_id; " +
            "DELETE FROM social_group_request WHERE account_id = acc_id AND group_id = :groupId; " +
            "INSERT INTO social_group_member VALUES (acc_id, :groupId); " +
            "END $$")
    void acceptRequest(String username, int groupId);

    @Modifying
    @Query("DELETE FROM social_group_member WHERE account_id = " +
            "(SELECT id FROM account WHERE username LIKE :username) AND group_id = :groupId")
    void deleteMember(String username, int groupId);

    @Modifying
    @Query("DELETE FROM social_group_request WHERE account_id = " +
            "(SELECT id FROM account WHERE username LIKE :username) AND group_id = :groupId")
    void deleteRequest(String accountId, int groupId);
}
