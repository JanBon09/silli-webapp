package org.silli.sillibackend.services;

import jdk.jshell.spi.ExecutionControl;
import org.silli.sillibackend.models.GroupDto;
import org.silli.sillibackend.repositories.GroupMembershipRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupMembershipService {
    private final GroupMembershipRepository groupMembershipRepository;

    public GroupMembershipService(GroupMembershipRepository groupMembershipRepository) {
        this.groupMembershipRepository = groupMembershipRepository;
    }

    public void joinAttempt(GroupDto groupDto, String username)  throws ExecutionControl.NotImplementedException {
        switch(groupDto.getAccessibility()){
            case "OPEN":
                groupMembershipRepository.openAddMember(username, groupDto.getId());
                break;
            case "PRIVATE":
                break;
            case "HIDDEN":
                break;
            default:
                throw new ExecutionControl.NotImplementedException("No such accessibility type");
        }
    }
}
