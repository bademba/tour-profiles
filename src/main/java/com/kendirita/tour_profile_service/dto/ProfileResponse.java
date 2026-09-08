package com.kendirita.tour_profile_service.dto;


import com.kendirita.tour_profile_service.entity.Profile;

import java.util.Date;

public class ProfileResponse {

    private String id;
    private String phone;
    private String avatarUrl;
    private String email;
    private Date createdAt;
    private Date updatedAt;

    public static ProfileResponse from(Profile profile) {
        if (profile == null) return null;

        ProfileResponse dto = new ProfileResponse();
        dto.id = profile.getId();
        dto.phone = profile.getPhone();
        dto.avatarUrl = profile.getAvatarUrl();
        dto.email = profile.getEmail();
        dto.createdAt =profile.getCreatedAt();
        dto.updatedAt = profile.getUpdatedAt();
        return dto;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}