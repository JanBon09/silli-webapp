package org.silli.sillibackend.controllers;

import jdk.jshell.spi.ExecutionControl;
import org.silli.sillibackend.models.GroupDto;
import org.silli.sillibackend.services.GroupRequestService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

// GroupRequest logic is mapping actions that non-owner user can take towards other people groups.
// It describes situations like:
// - Joining group
// - Sending a request to join the group
// - Canceling a request
// - Leaving group / Cancel membership

@RestController
@RequestMapping("group")
public class GroupRequestController {
    private final GroupRequestService groupRequestService;

    public GroupRequestController(GroupRequestService groupRequestService) {
        this.groupRequestService = groupRequestService;
    }

    @PostMapping("/join")
    public ResponseEntity<Object> join(Authentication authentication, @RequestBody GroupDto groupDto){
        try{
            groupRequestService.joinAttempt(authentication, groupDto);
        } catch(ExecutionControl.NotImplementedException e){
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<Object> cancel(Authentication authentication, @RequestParam String entity,
                                                @RequestBody GroupDto groupDto){
        try{
            groupRequestService.delete(authentication, entity, groupDto);
        }catch (ChangeSetPersister.NotFoundException e){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.ok().build();
    }

}
