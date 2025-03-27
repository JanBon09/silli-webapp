package org.silli.sillibackend.controllers;

import jdk.jshell.spi.ExecutionControl;
import org.silli.sillibackend.models.GroupDto;
import org.silli.sillibackend.services.GroupMembershipService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

// GroupMembership logic is mapping actions that owner of a group can  take towards its members.
// It describes situations like:
// - Accept a join request
// - Deny a join request
// (To do) - Inviting users to group
@RestController
@RequestMapping("group")
public class GroupMembershipController {
    private final GroupMembershipService groupMembershipService;

    public GroupMembershipController(GroupMembershipService groupMembershipService) {
        this.groupMembershipService = groupMembershipService;
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Object> remove(Authentication authentication, @RequestParam String memberUsername,
                                         @RequestParam String entity, GroupDto groupDto) {
        try{
            groupMembershipService.delete(authentication, groupDto, entity, memberUsername);
        } catch(AuthorizationServiceException e){
            ResponseEntity.status(401).build();
        } catch(ExecutionControl.NotImplementedException e){
            ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/accept")
    public ResponseEntity<Object> acceptRequest(Authentication authentication, @RequestParam String memberUsername,
                                         GroupDto groupDto) {
        try{
            groupMembershipService.accept(authentication, groupDto, memberUsername);
        } catch (AuthorizationServiceException e){
            ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok().build();
    }
}
