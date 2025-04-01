package org.silli.sillibackend.services;

import org.silli.sillibackend.models.Group;
import org.silli.sillibackend.models.GroupDto;
import org.silli.sillibackend.repositories.GroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final EntityManipulationAuthService entityManipulationAuthService;

    public GroupService(GroupRepository groupRepository, EntityManipulationAuthService entityManipulationAuthService) {
        this.groupRepository = groupRepository;
        this.entityManipulationAuthService = entityManipulationAuthService;
    }

    public Page<Group> findPageSortedBy(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    public void persist(String subject, GroupDto groupDto) {
        groupRepository.save(groupDto.getName(), LocalDateTime.now(), groupDto.getAccessibility(), subject);
    }

    public void changeName(String subject, int groupId, String newName)
            throws AuthorizationServiceException{
        if(!entityManipulationAuthService.authBySubjectAndEntity(subject, groupId, groupRepository)){
            throw new AuthorizationServiceException("Unathorized");
        }

        groupRepository.updateName(groupId, newName);
    }

    public void delete(String subject, int groupId) throws AuthorizationServiceException{
        if(!entityManipulationAuthService.authBySubjectAndEntity(subject, groupId, groupRepository)){
            throw new AuthorizationServiceException("Unathorized");
        }

        groupRepository.delete(groupId);
    }

}
