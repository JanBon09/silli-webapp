package org.silli.sillibackend.services;

import org.silli.sillibackend.models.Group;
import org.silli.sillibackend.models.GroupDto;
import org.silli.sillibackend.repositories.GroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final EntityManipulationAuthService entityManipulationAuthService;
    private final JwtDecodingService jwtDecodingService;

    public GroupService(GroupRepository groupRepository, EntityManipulationAuthService entityManipulationAuthService,
                        JwtDecodingService jwtDecodingService) {
        this.groupRepository = groupRepository;
        this.entityManipulationAuthService = entityManipulationAuthService;
        this.jwtDecodingService = jwtDecodingService;
    }

    public Page<Group> findPageSortedBy(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    public void persist(Authentication authentication, GroupDto groupDto) {
        String username = jwtDecodingService.decodeUsernameFromJWT(authentication);

        groupRepository.save(groupDto.getName(), LocalDateTime.now(), groupDto.getAccessibility(), username);
    }

    public void changeName(Authentication authentication, GroupDto groupDto, String newName)
            throws AuthorizationServiceException{
        if(!entityManipulationAuthService.checkAuthByEntity(authentication, groupDto)){
            throw new AuthorizationServiceException("Unathorized");
        }

        groupRepository.updateName(groupDto.getName(), newName);
    }

    public void delete(Authentication authentication, GroupDto groupDto) throws AuthorizationServiceException{
        if(!entityManipulationAuthService.checkAuthByEntity(authentication, groupDto)){
            throw new AuthorizationServiceException("Unathorized");
        }

        groupRepository.delete(groupDto.getId());
    }

}
