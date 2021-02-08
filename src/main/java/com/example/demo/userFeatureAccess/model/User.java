package com.example.demo.userFeatureAccess.model;
import javax.persistence.*;


@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @Column(name="Email")
    private String email;

    @Column(name="FeatureName")
    private String featureName;

    @Column(name="Enable")
    private Boolean enable;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
