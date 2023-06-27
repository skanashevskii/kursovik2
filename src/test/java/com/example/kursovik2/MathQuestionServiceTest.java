package com.example.kursovik2;

import com.example.kursovik2.model.Question;
import com.example.kursovik2.repository.QuestionRepository;
import com.example.kursovik2.service.MathQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private MathQuestionService mathQuestionService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testAddQuestion() {
        Question question = mathQuestionService.add("What is 2 + 2?", "4");

        assertNotNull(question);
        assertEquals("What is 2 + 2?", question.getQuestion());
        assertEquals("4", question.getAnswer());
        assertTrue(mathQuestionService.getAllQuestions().contains(question));
    }

    @Test
    void testRemoveQuestion() {
        Question question = mathQuestionService.add("2+2", "4");
        mathQuestionService.remove("2+2", "4");
        assertNotNull(question);
        assertFalse(mathQuestionService.getAllQuestions().contains(question));
    }

    @Test
    void testGetAllQuestions() {

        Question question1 = mathQuestionService.add("What is 2 + 2?", "4");
        Question question2 = mathQuestionService.add("What is 3 * 4?", "12");

        assertEquals(2, mathQuestionService.getAllQuestions().size());
        assertTrue(mathQuestionService.getAllQuestions().contains(question1));
        assertTrue(mathQuestionService.getAllQuestions().contains(question2));
        //verify(questionRepository, times(1)).getAllQuestions();

    }

    @Test
    void testGetRandomQuestion() {
        Question question1 = mathQuestionService.add("What is 2 + 2?", "4");
        Question question2 = mathQuestionService.add("What is 3 * 4?", "12");

        Question randomQuestion = mathQuestionService.getRandomQuestion();
        assertTrue(mathQuestionService.getAllQuestions().contains(randomQuestion));

        //verify(mathQuestionService, times(1)).getAllQuestions();
    }
}
