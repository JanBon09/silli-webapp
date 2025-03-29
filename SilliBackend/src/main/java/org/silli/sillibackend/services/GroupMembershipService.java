package org.silli.sillibackend.services;

import jdk.jshell.spi.ExecutionControl;
import org.silli.sillibackend.repositories.GroupMembershipRepository;
import org.silli.sillibackend.repositories.GroupRepository;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class GroupMembershipService {
    private final GroupMembershipRepository groupMembershipRepository;
    private final EntityManipulationAuthService entityManipulationAuthService;
    private final GroupRepository groupRepository;

    public GroupMembershipService(GroupMembershipRepository groupMembershipRepository,
                                  EntityManipulationAuthService entityManipulationAuthService, GroupRepository groupRepository) {
        this.groupMembershipRepository = groupMembershipRepository;
        this.entityManipulationAuthService = entityManipulationAuthService;
        this.groupRepository = groupRepository;
    }

    // Service method for deleting members or requests as a group owner
    public void delete(Authentication authentication, int groupId, String entity, String username)
    throws AuthorizationServiceException, ExecutionControl.NotImplementedException {
        if(!entityManipulationAuthService.checkAuthByEntity(authentication, groupId, groupRepository)) {
            throw new AuthorizationServiceException("Unauthorized");
        }

        switch(entity){
            case "REQUEST":
                groupMembershipRepository.deleteRequest(username, groupId);
                break;
            case "MEMBER":
                groupMembershipRepository.deleteMember(username, groupId);
                break;
            default:
                throw new ExecutionControl.NotImplementedException("No such accessibility type");
        }
    }

    // Accepting user request for joining a group
    public void accept(Authentication authentication, int groupId, String username) throws AuthorizationServiceException {
        if(!entityManipulationAuthService.checkAuthByEntity(authentication, groupId, groupRepository)) {
            throw new AuthorizationServiceException("Unauthorized");
        }

        groupMembershipRepository.acceptRequest(username, groupId);
    }
}
