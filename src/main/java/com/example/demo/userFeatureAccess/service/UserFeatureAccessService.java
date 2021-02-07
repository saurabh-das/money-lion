package com.example.demo.userFeatureAccess.service;

import com.example.demo.userFeatureAccess.model.User;
import com.example.demo.userFeatureAccess.repository.UserFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserFeatureAccessService implements IUserFeatureAccess {



    @Autowired
    private UserFeatureRepository userFeatureRepository;

    @Override
    public boolean checkFeatureAccess(String email, String featureName) {

        User user =  userFeatureRepository.findByEmailAndFeatureName(email, featureName);
        if(user != null) {
            return user.getEnable();
        }
       return false;
    }

    @Override
    public User updateUserWithFeature(User userFeatureRequestDto) {

        boolean validEmail = false;

        if(userFeatureRequestDto.getEmail()!= null){
             validEmail = verifyEmail(userFeatureRequestDto.getEmail());
        }
        if(validEmail && userFeatureRequestDto.getFeatureName()!= null) {
            return  userFeatureRepository.save(userFeatureRequestDto);
        } else {
            return null;
        }
    }

    private boolean verifyEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
