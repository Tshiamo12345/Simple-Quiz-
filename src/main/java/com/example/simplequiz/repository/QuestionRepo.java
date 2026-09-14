package com.example.simplequiz.repository;

import com.example.simplequiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,String> {

    long countByQuizId(String quizId);

    List<Question> findByQuizId(String quizId);


}
