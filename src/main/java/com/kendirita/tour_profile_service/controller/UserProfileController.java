package com.kendirita.tour_profile_service.controller;

import com.kendirita.tour_profile_service.dto.ProfileResponse;
import com.kendirita.tour_profile_service.entity.Profile;
import com.kendirita.tour_profile_service.repository.UserProfileRepository;
import com.kendirita.tour_profile_service.response.ResponseHandler;
import com.kendirita.tour_profile_service.service.UserProfileService;
import com.kendirita.tour_profile_service.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v2/tour")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @PostMapping("/users/user-profile")
    public ResponseEntity<Object> createUserProfile(@RequestBody Profile profile) {
        Profile createdUserProfile = userProfileService.createUserProfile(profile);
        if(createdUserProfile == null){
            return ResponseHandler.generateResponse(UUID.randomUUID(), "User profile already exists", HttpStatus.CONFLICT, null, TimestampUtil.now()
            );
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(), "User profile created", HttpStatus.CREATED, ProfileResponse.from(createdUserProfile),TimestampUtil.now());
    }

    @GetMapping("/users/user-profile/{email}")
    public ResponseEntity<Object> searchByEmail(@PathVariable String email){
        Profile userEmail = userProfileService.searchByEmail(email);
        if(userEmail==null){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"User profile not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"User profile  found",HttpStatus.OK,ProfileResponse.from(userEmail),TimestampUtil.now());
    }

    @GetMapping("/users/user-profile")
    public ResponseEntity<Object> listUserProfiles(){
        List<Profile> userProfiles = userProfileService.listUsersProfile();
        List<ProfileResponse> userProfileResponses =userProfiles.stream().map(ProfileResponse::from).toList();
        return ResponseHandler.generateResponse(UUID.randomUUID(),"User profiles found",HttpStatus.OK,userProfileResponses,TimestampUtil.now());
    }

    @PutMapping("/users/user-profile/{email}")
    public ResponseEntity<Object> updateUserProfile(@RequestBody Profile profile, @PathVariable String email){
        Profile currentUserProfile = userProfileService.searchByEmail(email);
        if(currentUserProfile==null){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"User profile not found",HttpStatus.NOT_FOUND,"",TimestampUtil.now());
        }
//        currentUserProfile.setFullName(profile.getEmail());

        //update existing user profile
        if(currentUserProfile != null) {
            currentUserProfile.setFullName(profile.getFullName());
            currentUserProfile.setPhone(profile.getPhone());
            currentUserProfile.setAvatarUrl(profile.getAvatarUrl());

        }
        Profile updatedUserProfile = userProfileRepository.save(currentUserProfile);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"User profile updated",HttpStatus.OK,ProfileResponse.from(updatedUserProfile),TimestampUtil.now());
    }

    @DeleteMapping("/users/user-profile/{email}")
    public ResponseEntity<Object> deleteUserProfileByEmail(@PathVariable String email) {

        boolean deleted = userProfileService.deleteByEmail(email);

        if (!deleted) {
            return ResponseHandler.generateResponse(UUID.randomUUID(), "User profile not found", HttpStatus.NOT_FOUND, null, TimestampUtil.now());
        }

        return ResponseHandler.generateResponse(UUID.randomUUID(), "User profile deleted successfully", HttpStatus.NO_CONTENT, null, TimestampUtil.now());
    }
}
