package com.example.demo.userFeatureAccess.repository;

import com.example.demo.userFeatureAccess.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFeatureRepository extends CrudRepository<User, Integer> {

    User findByEmailAndFeatureName(String email, String featureName);
}
