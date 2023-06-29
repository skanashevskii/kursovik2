package com.example.kursovik2;

import com.example.kursovik2.exception.BadRequestException;
import com.example.kursovik2.model.Question;
import com.example.kursovik2.service.ExaminerServiceImpl;
import com.example.kursovik2.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    private ExaminerServiceImpl examinerService;

    @Mock
    private QuestionService javaQuestionService;

    @Mock
    private QuestionService mathQuestionService;

    @BeforeEach
    public void setUp() {

        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
    }

//=================================================================================
    @Test
    public void getQuestions() {

        List<Question> javaQuestions = new ArrayList<>();
        javaQuestions.add(new Question("Java Question 1", "Java Answer 1"));
        javaQuestions.add(new Question("Java Question 2", "Java Answer 2"));

        List<Question> mathQuestions = new ArrayList<>();
        mathQuestions.add(new Question("Math Question 1", "Math Answer 1"));
        mathQuestions.add(new Question("Math Question 2", "Math Answer 2"));

        when(javaQuestionService.getAllQuestions()).thenReturn(javaQuestions);
        when(mathQuestionService.getAllQuestions()).thenReturn(mathQuestions);

        int amount = 4;
        Collection<Question> questions = examinerService.getQuestions(amount);

        assertEquals(amount, questions.size());

        // Проверка на содержание вопросов из обоих тем
        assertTrue(questions.stream().anyMatch(javaQuestions::contains));
        assertTrue(questions.stream().anyMatch(mathQuestions::contains));

        // Убедится что javaQuestionService.getRandomQuestion() and mathQuestionService.getRandomQuestion()
        // методы вызвались определенное количество раз
        verify(javaQuestionService, times(amount)).getRandomQuestion();
        verify(mathQuestionService, times(amount)).getRandomQuestion();
    }

    @Test
    void getQuestions_NotEnoughQuestionsAvailable() {
        List<Question> javaQuestions = new ArrayList<>();
        javaQuestions.add(new Question("Java Question 1", "Java Answer 1"));
        javaQuestions.add(new Question("Java Question 2", "Java Answer 2"));

        List<Question> mathQuestions = new ArrayList<>();
        mathQuestions.add(new Question("Math Question 1", "Math Answer 1"));

        when(javaQuestionService.getAllQuestions()).thenReturn(javaQuestions);
        when(mathQuestionService.getAllQuestions()).thenReturn(mathQuestions);

        int amount = 4;

        assertThrows(BadRequestException.class, () -> {
            examinerService.getQuestions(amount);
        });

        verify(javaQuestionService, never()).getRandomQuestion();
        verify(mathQuestionService, never()).getRandomQuestion();
    }
}