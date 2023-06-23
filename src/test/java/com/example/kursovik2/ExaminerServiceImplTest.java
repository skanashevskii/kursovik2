package com.example.kursovik2;

import com.example.kursovik2.exception.BadRequestException;
import com.example.kursovik2.model.Question;
import com.example.kursovik2.service.ExaminerServiceImpl;
import com.example.kursovik2.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;


@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    private ExaminerServiceImpl examinerService;
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = mock(QuestionService.class);
        examinerService = new ExaminerServiceImpl(questionService);
    }

    @Test
    void testGetQuestions() {

        Question question1 = new Question("Question 1", "Answer 1");
        Question question2 = new Question("Question 2", "Answer 2");
        Question question3 = new Question("Question 3", "Answer 3");
        when(questionService.getAll()).thenReturn(Arrays.asList(question1, question2, question3));
        when(questionService.getRandomQuestion()).thenReturn(question1, question2, question3);

        int amount = 2;
        Collection<Question> questions = examinerService.getQuestions(amount);

        assertEquals(amount, questions.size());
        assertTrue(questions.contains(question1));
        assertTrue(questions.contains(question2));
        assertFalse(questions.contains(question3));
    }

    @Test
    void testGetQuestionsWithInvalidAmount() {

        when(questionService.getAll()).thenReturn(Arrays.asList(new Question("Question 1", "Answer 1")));

        int amount = 2;
        assertThrows(BadRequestException.class, () -> examinerService.getQuestions(amount));
    }
}
