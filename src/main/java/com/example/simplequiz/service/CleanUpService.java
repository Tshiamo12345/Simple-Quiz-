package com.example.simplequiz.service;


import com.example.simplequiz.repository.PendingUserRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@EnableScheduling
public class CleanUpService {


    private final PendingUserRepository pendingUserRepository;

    public CleanUpService(PendingUserRepository pendingUserRepository){
        this.pendingUserRepository = pendingUserRepository;
    }


    @Scheduled(fixedRate = 60000)
    @Transactional
    public void cleanExpiredPendingUsers(){

        pendingUserRepository.deleteByExpiryTimestampBefore(LocalDateTime.now());
    }
}
