package com.konate.music_application.UserService.DataLayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
//    User findByEmail(String email);
//    User findByUserId(String userId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User findAllByUserIdentifier_UserId(String userId);
    User findByEmail(String email);
}
