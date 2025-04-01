package org.silli.sillibackend.services;

import jdk.jshell.spi.ExecutionControl;
import org.silli.sillibackend.models.GroupDto;
import org.silli.sillibackend.repositories.GroupRequestRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class GroupRequestService {
    private final GroupRequestRepository groupRequestRepository;

    public GroupRequestService(GroupRequestRepository groupRequestRepository) {
        this.groupRequestRepository = groupRequestRepository;
    }

    public void joinAttempt(String subject, GroupDto groupDto)  throws ExecutionControl.NotImplementedException {
        switch(groupDto.getAccessibility()){
            case "OPEN":
                groupRequestRepository.openAddMember(subject, groupDto.getId());
                break;
            case "PRIVATE":
                groupRequestRepository.addRequest(subject, groupDto.getId());
                break;
            case "HIDDEN":
                break;
            default:
                throw new ExecutionControl.NotImplementedException("No such accessibility type");
        }
    }

    public void delete(String subject, String entity, GroupDto groupDto) throws ChangeSetPersister.NotFoundException {
        switch(entity){
            case "REQUEST":
                groupRequestRepository.deleteRequest(subject, groupDto.getId());
                break;
            case "MEMBERSHIP":
                groupRequestRepository.deleteMembership(subject, groupDto.getId());
                break;
        }
    }

}
