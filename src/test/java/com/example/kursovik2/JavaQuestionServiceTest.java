package com.example.kursovik2;

import com.example.kursovik2.model.Question;
import com.example.kursovik2.repository.QuestionRepository;
import com.example.kursovik2.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class JavaQuestionServiceTest {
    private JavaQuestionService javaQuestionService;

    @Mock
    private QuestionRepository questionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        javaQuestionService = new JavaQuestionService(questionRepository);
    }

    @Test
    void addQuestion() {
        Question question = new Question("Question", "Answer");

        javaQuestionService.addQuestion(question);

        verify(questionRepository, times(1)).add(question);
    }

    @Test
    void removeQuestion() {
        Question question = new Question("Question", "Answer");

        javaQuestionService.removeQuestion(question);

        verify(questionRepository, times(1)).remove(question);
    }

    @Test
    void getAllQuestions() {
        List<Question> expectedQuestions = new ArrayList<>();
        expectedQuestions.add(new Question("Question 1", "Answer 1"));
        expectedQuestions.add(new Question("Question 2", "Answer 2"));

        when(questionRepository.getAll()).thenReturn(expectedQuestions);

        Collection<Question> actualQuestions = javaQuestionService.getAllQuestions();

        assertEquals(expectedQuestions.size(), actualQuestions.size());
        assertTrue(actualQuestions.containsAll(expectedQuestions));
    }

    @Test
    void getRandomQuestion() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Question 1", "Answer 1"));
        questions.add(new Question("Question 2", "Answer 2"));

        when(questionRepository.getAll()).thenReturn(questions);

        try {
            Question randomQuestion = javaQuestionService.getRandomQuestion();
            assertTrue(questions.contains(randomQuestion));
        } catch (RuntimeException e) {
            fail("No question should throw an exception.");
        }
    }

    @Test
    void getRandomQuestion_NoQuestionsAvailable() {
        when(questionRepository.getAll()).thenReturn(new ArrayList<>());

        assertThrows(RuntimeException.class, () -> {
            javaQuestionService.getRandomQuestion();
        });
    }
}
