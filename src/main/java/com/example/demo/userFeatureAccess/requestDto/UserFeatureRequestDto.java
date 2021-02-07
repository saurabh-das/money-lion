package com.example.demo.userFeatureAccess.requestDto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class UserFeatureRequestDto {

    @JsonProperty("featureName")
    private String featureName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("enable")
    private boolean enable;

}
