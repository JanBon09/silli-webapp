package org.silli.sillibackend.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

// GroupRequestRepository and GroupMembershipRepository could be merged but services and controllers should stay
// as standalone components
public interface GroupRequestRepository extends CrudRepository<GroupRequestRepository, Integer> {
    @Modifying
    @Query("INSERT INTO social_group_member VALUES ((SELECT id FROM account WHERE username LIKE :username), :groupId)")
    void openAddMember(String username, int groupId);

    @Modifying
    @Query("INSERT INTO social_group_request VALUES ((SELECT id FROM account WHERE username LIKE :username), :groupId)")
    void addRequest(String username, int groupId);

    @Modifying
    @Query("DELETE FROM social_group_request WHERE account_id = " +
            "(SELECT id FROM account WHERE username LIKE :username) AND group_id = :groupdId")
    void deleteRequest(String username, int groupId);

    @Modifying
    @Query("DELETE FROM social_group_member WHERE account_id = " +
            "(SELECT id FROM account WHERE username LIKE :username) AND group_id = :groupId")
    void deleteMembership(String username, int groupId);
}
