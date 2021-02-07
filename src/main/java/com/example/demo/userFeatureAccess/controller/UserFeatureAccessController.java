package com.example.demo.userFeatureAccess.controller;

import com.example.demo.userFeatureAccess.model.User;
import com.example.demo.userFeatureAccess.service.IUserFeatureAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserFeatureAccessController {

    @Autowired
    private IUserFeatureAccess featureAccess;

    @GetMapping("/feature")
    @ResponseBody
    public boolean checkFeatureAccess(@RequestParam String email,@RequestParam  String featureName) {
        boolean canAccess = featureAccess.checkFeatureAccess(email, featureName);
        return canAccess;
    }

    @PostMapping("/feature")
    @ResponseBody
    public ResponseEntity updateUserWithFeature(@RequestBody User userFeatureRequestDto){

        User user = featureAccess.updateUserWithFeature(userFeatureRequestDto);

        if(user.equals(null)){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        else
            return ResponseEntity.status(HttpStatus.OK).build();
    }

}

