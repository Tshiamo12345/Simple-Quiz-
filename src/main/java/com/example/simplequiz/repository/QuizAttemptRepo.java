package com.example.simplequiz.repository;

import com.example.simplequiz.model.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizAttemptRepo extends JpaRepository<QuizAttempt,String> {


}
