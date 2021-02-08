package com.example.demo;

import com.example.demo.userFeatureAccess.model.User;
import com.example.demo.userFeatureAccess.repository.UserFeatureRepository;
import com.example.demo.userFeatureAccess.service.UserFeatureAccessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import static org.junit.Assert.*;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserFeatureAccessServiceTest {

    @Autowired
    private UserFeatureAccessService userFeatureAccessService;

    @MockBean
    private UserFeatureRepository userFeatureRepository;

    @TestConfiguration
    static class UserFeatureAccessServiceTestConfig {
        @Bean
        public UserFeatureAccessService userFeatureAccessService() {
            return new UserFeatureAccessService();
        }
    }

    @Test
    public void updateUserWithFeature_Success() {
        User user = getUser();

        Mockito.when(userFeatureRepository.save(user)).thenReturn(user);
        User savedUser = userFeatureAccessService.updateUserWithFeature(user);
        assertEquals(user, savedUser);
    }

    private User getUser() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setFeatureName("testAccess");
        user.setEnable(true);
        return user;
    }

    @Test
    public void updateUserWithFeature_Fail_InvalidEmailFormat() {
        User user = getUser();
        user.setEmail("testEmail");

        User savedUser = userFeatureAccessService.updateUserWithFeature(user);
        assertEquals(null, savedUser);

    }

    @Test
    public void updateUserWithFeature_Fail_NullFeatureName() {
        User user =getUser();
        user.setFeatureName(null);

        User savedUser = userFeatureAccessService.updateUserWithFeature(user);
        assertEquals(null, savedUser);

    }

    @Test
    public void checkFeatureAccess_Success() {
        User user = getUser();

        Mockito.when(userFeatureRepository.findByEmailAndFeatureName("test@gmail.com", "testAccess")).thenReturn(user);
        boolean checkFeatureAccessValue = userFeatureAccessService.checkFeatureAccess("test@gmail.com", "testAccess");
        assertEquals(checkFeatureAccessValue, user.getEnable());
    }

    @Test
    public void checkFeatureAccess_Fail() {
        Mockito.when(userFeatureRepository.findByEmailAndFeatureName("test@gmail.com", "testAccess")).thenReturn(null);
        boolean checkFeatureAccessValue = userFeatureAccessService.checkFeatureAccess("test@gmail.com", "testAccess");
        assertEquals(checkFeatureAccessValue, false);
    }

}
