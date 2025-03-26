package org.silli.sillibackend.controllers;

import org.silli.sillibackend.models.Group;
import org.silli.sillibackend.models.GroupDto;
import org.silli.sillibackend.services.GroupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public Page<Group> getGroupsInPages(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy,
            @RequestParam Boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return groupService.findPageSortedBy(pageable);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createGroup(Authentication authentication, @RequestBody GroupDto groupDto){
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String username = jwt.getSubject();

        groupService.persist(groupDto, username);

        return ResponseEntity.ok().build();
    }
}
