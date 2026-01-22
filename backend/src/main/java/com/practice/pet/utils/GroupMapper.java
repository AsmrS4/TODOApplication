package com.practice.pet.utils;

import com.practice.pet.dto.Group;
import com.practice.pet.dto.GroupRequest;
import com.practice.pet.entities.GroupEntity;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {
    public Group mapToGroup(GroupEntity entity) {
        Group group = new Group();
        group.setId(entity.getId());
        group.setGroupName(entity.getGroupName());
        group.setCreateTime(entity.getCreateTime());
        return group;
    }

    public GroupEntity mapToEntity(GroupRequest request) {
        GroupEntity entity = new GroupEntity();
        entity.setGroupName(request.getGroupName());
        return entity;
    }
}
