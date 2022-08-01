package com.codeus.dto;

import com.codeus.domain.Project;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProjectRequest {

    private String userid;
    private String groupName;
    private String projId;
    private String projCode;
    private String projStack;

    public Project toEntity() {
        return Project.builder()
                .projCode(projCode)
                .projId(projId)
                .projStack(projStack)
                .build();
    }
}
