package com.example.simplequiz.service;

import com.example.simplequiz.repository.QuizRepo;
import com.example.simplequiz.repository.UserRepo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {

    @Mock
    private QuizRepo quizRepo;

    @Mock
    private UserRepo userRepo;


}
