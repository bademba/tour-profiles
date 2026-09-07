package com.kendirita.tour_profile_service.repository;

import com.kendirita.tour_profile_service.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<Profile,String> {

    boolean existsByEmail(String email);
    Profile searchByEmail(String email);
    Optional<Profile> findByEmail(String email);
}
