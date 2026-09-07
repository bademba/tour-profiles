package com.kendirita.tour_profile_service.service;

import com.kendirita.tour_profile_service.entity.Profile;
import com.kendirita.tour_profile_service.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    //create new user profile
    @Transactional
    public Profile createUserProfile(Profile profile) {

        if (userProfileRepository.existsByEmail(profile.getEmail())) {
            throw new IllegalStateException("User with this email already exists");
        }
        return userProfileRepository.save(profile);
    }

    //search user profile by email
    public Profile searchByEmail(String email){
        return userProfileRepository.searchByEmail(email);
    }

    //fetch all users profiles
    public List<Profile> listUsersProfile(){
        return userProfileRepository.findAll();
    }

    public boolean deleteByEmail(String email) {
        Optional<Profile> userProfile = Optional.ofNullable(userProfileRepository.searchByEmail(email));
        if (userProfile.isEmpty()) {
            return false;
        }
        userProfileRepository.delete(userProfile.get());
        return true;
    }
}
