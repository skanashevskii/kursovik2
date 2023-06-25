package com.example.kursovik2;

import com.example.kursovik2.model.Question;
import com.example.kursovik2.repository.QuestionRepository;
import com.example.kursovik2.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private JavaQuestionService javaQuestionService;

   /* @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }*/
    @Test
    void addQuestion_ShouldAddQuestionToRepository() {
        String question = "What is Java?";
        String answer = "Java is a programming language.";
        javaQuestionService.add(question, answer);

        verify(questionRepository, times(1)).add(new Question(question, answer));
    }

    @Test
    void testAddQuestion() {
        Question question = javaQuestionService.add("Столица Франции?", "Paris");
        assertTrue(javaQuestionService.getAllQuestions().contains(question));
    }

    @Test
    void testRemoveQuestion() {
        Question question = javaQuestionService.add("Столица Германии?", "Berlin");
        javaQuestionService.remove("Столица Германии?", "Berlin");
        assertFalse(javaQuestionService.getAllQuestions().contains(question));
    }

    @Test
    void testGetAllQuestions() {
        Question question1 = javaQuestionService.add("Столица Италии?", "Rome");
        Question question2 = javaQuestionService.add("Столица Испании?", "Madrid");
        Question question3 = javaQuestionService.add("Столица России?", "Moscow");
        assertEquals(3, javaQuestionService.getAllQuestions().size());
        assertTrue(javaQuestionService.getAllQuestions().contains(question1));
        assertTrue(javaQuestionService.getAllQuestions().contains(question2));
        assertTrue(javaQuestionService.getAllQuestions().contains(question3));
    }

    @Test
    void testGetRandomQuestion() {
        Question question1 = javaQuestionService.add("Вопрос 1", "Ответ 1");
        Question question2 = javaQuestionService.add("Вопрос 2", "Ответ 2");
        Question question3 = javaQuestionService.add("Вопрос 3", "Ответ 3");
        Question randomQuestion = javaQuestionService.getRandomQuestion();
        assertTrue(javaQuestionService.getAllQuestions().contains(randomQuestion));
    }

    @Test
    void testGetRandomQuestionWhenEmpty() {
        assertNull(javaQuestionService.getRandomQuestion());
    }
}
