package org.silli.sillibackend.services;

import jdk.jshell.spi.ExecutionControl;
import org.silli.sillibackend.models.GroupDto;
import org.silli.sillibackend.repositories.GroupMembershipRepository;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class GroupMembershipService {
    private final GroupMembershipRepository groupMembershipRepository;
    private final EntityManipulationAuthService entityManipulationAuthService;

    public GroupMembershipService(GroupMembershipRepository groupMembershipRepository,
                                  EntityManipulationAuthService entityManipulationAuthService) {
        this.groupMembershipRepository = groupMembershipRepository;
        this.entityManipulationAuthService = entityManipulationAuthService;
    }

    // Service method for deleting members or requests as a group owner
    public void delete(Authentication authentication, GroupDto groupDto, String entity, String username)
    throws AuthorizationServiceException, ExecutionControl.NotImplementedException {
        if(!entityManipulationAuthService.checkAuthByEntity(authentication, groupDto)) {
            throw new AuthorizationServiceException("Unauthorized");
        }

        switch(entity){
            case "REQUEST":
                groupMembershipRepository.deleteRequest(username, groupDto.getId());
                break;
            case "MEMBER":
                groupMembershipRepository.deleteMember(username, groupDto.getId());
                break;
            default:
                throw new ExecutionControl.NotImplementedException("No such accessibility type");
        }
    }

    // Accepting user request for joining a group
    public void accept(Authentication authentication, GroupDto groupDto, String username) throws AuthorizationServiceException {
        if(!entityManipulationAuthService.checkAuthByEntity(authentication, groupDto)) {
            throw new AuthorizationServiceException("Unauthorized");
        }

        groupMembershipRepository.acceptRequest(username, groupDto.getId());
    }
}
