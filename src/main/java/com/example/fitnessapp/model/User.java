package com.example.fitnessapp.model;

import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "app_user")
//implements UserDetails
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    @OneToMany
    private List<ExerciseSchedule> exerciseSchedules;

    @OneToOne
    private ExerciseSchedule favorites;

    @OneToOne
    private BMI BMI;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(String username, String password, List<ExerciseSchedule> exerciseSchedules, ExerciseSchedule favorites, Role role, BMI BMI) {
        this.username = username;
        this.password = password;
        this.exerciseSchedules = exerciseSchedules;
        this.favorites = favorites;
        this.BMI = BMI;
        this.role=role;
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role=role;
    }

    public User() {
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(role);
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return isAccountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return isAccountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return isCredentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return isEnabled;
//    }
}
