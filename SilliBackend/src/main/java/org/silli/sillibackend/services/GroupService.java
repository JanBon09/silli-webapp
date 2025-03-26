package org.silli.sillibackend.services;

import org.silli.sillibackend.models.Group;
import org.silli.sillibackend.models.GroupDto;
import org.silli.sillibackend.repositories.GroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Page<Group> findPageSortedBy(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    public void persist(GroupDto groupDto, String username){
        groupRepository.save(groupDto.getName(), LocalDateTime.now(), groupDto.getAccessibility(), username);
    }

    public void changeName(GroupDto groupDto, String newName){


        groupRepository.updateName(groupDto.getName(), newName);
    }

    public void delete(GroupDto groupDto, String username){}

}
