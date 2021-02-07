package com.example.demo.userFeatureAccess.service;

import com.example.demo.userFeatureAccess.model.User;
import org.springframework.http.ResponseEntity;

public interface IUserFeatureAccess {
    boolean checkFeatureAccess(String email, String featureName);


    User updateUserWithFeature(User userRequest);
}
