package com.codeus.service;

import com.codeus.domain.Group_;
import com.codeus.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    @Transactional
    public Group_ createGroup(Group_ group){

        Group_ newGroup = groupRepository.save(group);
        return newGroup;
    }

    @Transactional
    public List<Group_> findAll(){//추가함
        List<Group_> groups  = groupRepository.findAll();
        return groups;
    }

    @Transactional
    public Group_ getGroup(Long groupSeq){
        Group_ group = groupRepository.findById(groupSeq).orElseThrow(()->new IllegalArgumentException("group을 찾을 수 없습니다."));
        return group;
    }
}
