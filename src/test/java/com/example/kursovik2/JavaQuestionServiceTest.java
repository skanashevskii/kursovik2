package com.example.kursovik2;

import com.example.kursovik2.model.Question;
import com.example.kursovik2.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private JavaQuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
    }

    @Test
    void testAddQuestion() {
        Question question = questionService.add("Столица Франции?", "Paris");
        assertTrue(questionService.getAll().contains(question));
    }

    @Test
    void testRemoveQuestion() {
        Question question = questionService.add("Столица Германии?", "Berlin");
        questionService.remove("Столица Германии?", "Berlin");
        assertFalse(questionService.getAll().contains(question));
    }

    @Test
    void testGetAllQuestions() {
        Question question1 = questionService.add("Столица Италии?", "Rome");
        Question question2 = questionService.add("Столица Испании?", "Madrid");
        Question question3 = questionService.add("Столица России?", "Moscow");
        assertEquals(3, questionService.getAll().size());
        assertTrue(questionService.getAll().contains(question1));
        assertTrue(questionService.getAll().contains(question2));
        assertTrue(questionService.getAll().contains(question3));
    }

    @Test
    void testGetRandomQuestion() {
        Question question1 = questionService.add("Вопрос 1", "Ответ 1");
        Question question2 = questionService.add("Вопрос 2", "Ответ 2");
        Question question3 = questionService.add("Вопрос 3", "Ответ 3");
        Question randomQuestion = questionService.getRandomQuestion();
        assertTrue(questionService.getAll().contains(randomQuestion));
    }

    @Test
    void testGetRandomQuestionWhenEmpty() {
        assertNull(questionService.getRandomQuestion());
    }
}
