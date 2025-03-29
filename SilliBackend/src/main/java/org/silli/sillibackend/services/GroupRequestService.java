package org.silli.sillibackend.services;

import jdk.jshell.spi.ExecutionControl;
import org.silli.sillibackend.models.GroupDto;
import org.silli.sillibackend.repositories.AccountRepository;
import org.silli.sillibackend.repositories.GroupRequestRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class GroupRequestService {
    private final GroupRequestRepository groupRequestRepository;
    private final JwtDecodingService jwtDecodingService;

    public GroupRequestService(GroupRequestRepository groupRequestRepository, JwtDecodingService jwtDecodingService) {
        this.groupRequestRepository = groupRequestRepository;
        this.jwtDecodingService = jwtDecodingService;
    }

    public void joinAttempt(Authentication authentication, GroupDto groupDto)  throws ExecutionControl.NotImplementedException {
        String username = jwtDecodingService.decodeUsernameFromJWT(authentication);

        switch(groupDto.getAccessibility()){
            case "OPEN":
                groupRequestRepository.openAddMember(username, groupDto.getId());
                break;
            case "PRIVATE":
                groupRequestRepository.addRequest(username, groupDto.getId());
                break;
            case "HIDDEN":
                break;
            default:
                throw new ExecutionControl.NotImplementedException("No such accessibility type");
        }
    }

    public void delete(Authentication authentication, String entity, GroupDto groupDto) throws ChangeSetPersister.NotFoundException {
        String username = jwtDecodingService.decodeUsernameFromJWT(authentication);

        switch(entity){
            case "REQUEST":
                groupRequestRepository.deleteRequest(username, groupDto.getId());
                break;
            case "MEMBERSHIP":
                groupRequestRepository.deleteMembership(username, groupDto.getId());
                break;
        }
    }

}
