package com.example.simplequiz.repository;

import com.example.simplequiz.model.PendingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PendingUserRepository extends JpaRepository<PendingUser,String> {

    Optional<PendingUser> findByUsername(String username);
    Optional<PendingUser> findByEmail(String email);
    void deleteByExpiryTimestampBefore(LocalDateTime now);
}
