package org.silli.sillibackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jdk.jshell.spi.ExecutionControl;
import org.silli.sillibackend.services.GroupMembershipService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
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
    public ResponseEntity<Object> remove(HttpServletRequest request, @RequestParam String memberUsername,
                                         @RequestParam String entity, @RequestParam int groupId) {
        try{
            groupMembershipService.delete((String) request.getAttribute("Subject"), groupId, entity, memberUsername);
        } catch(AuthorizationServiceException e){
            ResponseEntity.status(401).build();
        } catch(ExecutionControl.NotImplementedException e){
            ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/accept")
    public ResponseEntity<Object> acceptRequest(HttpServletRequest request, @RequestParam String memberUsername,
                                         @RequestParam("group-id") int groupId) {
        try{
            groupMembershipService.accept((String) request.getAttribute("Subject"), groupId, memberUsername);
        } catch (AuthorizationServiceException e){
            ResponseEntity.status(401).build();
        }

        return ResponseEntity.ok().build();
    }
}
