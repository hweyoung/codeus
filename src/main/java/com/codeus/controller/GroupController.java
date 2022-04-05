package com.codeus.controller;

import com.codeus.domain.Group_;
import com.codeus.domain.User;
import com.codeus.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Group_> createPost(@RequestBody Group_ group){
        Group_ newGroup = groupService.createGroup(group);
        return ResponseEntity.ok(newGroup);
    }

    @GetMapping("/") //추가함
    public List<Group_> retrieveAllGroups() {
        return groupService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Group_> updateGroup(@PathVariable Long id, @RequestBody User user){
        Group_ group=groupService.getGroup(id);

        group.getUsers().add(user);

        Group_ updataGroup = groupService.createGroup(group);
        return ResponseEntity.ok(updataGroup);
    }
}
