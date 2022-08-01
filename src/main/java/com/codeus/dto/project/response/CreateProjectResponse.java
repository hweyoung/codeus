package com.codeus.dto;

import lombok.Getter;

@Getter
public class CreateProjectResponse {

    private String userid;
    private String groupName;
    private String projId;
    private String projCode;
    private String projStack;

    public CreateProjectResponse(String userid, String groupName, String projId, String projCode, String projStack) {
        this.userid = userid;
        this.groupName = groupName;
        this.projId = projId;
        this.projCode = projCode;
        this.projStack = projStack;
    }
}
