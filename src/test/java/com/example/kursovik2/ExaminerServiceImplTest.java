package com.example.kursovik2;

import com.example.kursovik2.exception.BadRequestException;
import com.example.kursovik2.model.Question;
import com.example.kursovik2.service.ExaminerServiceImpl;
import com.example.kursovik2.service.QuestionService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    @Qualifier("javaQuestionService")

    private QuestionService javaQuestionService;
    @Mock
    @Qualifier("mathQuestionService")
    private QuestionService mathQuestionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

  /*  @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }*/
    @Test
    void getQuestions_ShouldReturnRandomQuestionsFromBothServices() {
        // Настройка моков для возврата случайных вопросов
        when(javaQuestionService.getRandomQuestion()).thenReturn(new Question("Java Question 1", "Answer 1"));
        when(mathQuestionService.getRandomQuestion()).thenReturn(new Question("Math Question 1", "Answer 1"));

        List<Question> questions = examinerService.getRandomQuestions(1,1);
        // Проверка, что возвращены случайные вопросы из обоих сервисов
        assertThat(questions.size()).isEqualTo(2);
    }

    @Test
    void testGetQuestions() {

        Question question1 = new Question("Question 1", "Answer 1");
        Question question2 = new Question("Question 2", "Answer 2");
        Question question3 = new Question("Question 3", "Answer 3");
        when(examinerService.getRandomQuestions(1,1)).thenReturn(Arrays.asList(question1, question2, question3));


        int amount = 2;
        Collection<Question> questions = examinerService.getAllQuestions(amount);

        assertEquals(amount, questions.size());
        assertTrue(questions.contains(question1));
        assertTrue(questions.contains(question2));
        assertFalse(questions.contains(question3));
    }

    @Test
    void testGetQuestionsWithInvalidAmount() {

        when(javaQuestionService.getAllQuestions()).thenReturn(List.of(new Question("Question 1", "Answer 1")));

        int amount = 2;
        assertThrows(BadRequestException.class, () -> examinerService.getAllQuestions(amount));
    }
}
