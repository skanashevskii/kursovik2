package com.example.kursovik2;

import com.example.kursovik2.exception.BadRequestException;
import com.example.kursovik2.model.Question;
import com.example.kursovik2.service.ExaminerServiceImpl;
import com.example.kursovik2.service.QuestionService;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Mock
    private QuestionService javaQuestionService;

    @Mock
    private QuestionService mathQuestionService;

    @BeforeEach
    public void setUp() {

        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
    }


  /*  @Test
    public void getQuestionsTest() {

        Collection<Question> javaQuestions = new ArrayList<>();
        javaQuestions.add(new Question("Java Question 1", "Java Answer 1"));
        javaQuestions.add(new Question("Java Question 2", "Java Answer 2"));

        Collection<Question> mathQuestions = new ArrayList<>();
        mathQuestions.add(new Question("Math Question 1", "Math Answer 1"));
        mathQuestions.add(new Question("Math Question 2", "Math Answer 2"));

        when(javaQuestionService.getAllQuestions()).thenReturn(javaQuestions);
        when(mathQuestionService.getAllQuestions()).thenReturn(mathQuestions);

        int amount = 2;
        Collection<Question> questions = examinerService.getQuestions(amount);

        assertEquals(amount, questions.size());
       *//* assertTrue(questions.containsAll(javaQuestions));
        assertTrue(questions.containsAll(mathQuestions));*//*
        System.out.println(questions);
        System.out.println(javaQuestions);

        // Проверка на содержание вопросов из обоих тем
        assertTrue(CollectionUtils.containsAny(questions,javaQuestions));
        assertTrue(questions.stream().anyMatch(javaQuestions::contains));
        assertTrue(questions.stream().anyMatch(mathQuestions::contains));

        // Убедится что javaQuestionService.getRandomQuestion() and mathQuestionService.getRandomQuestion()
        // методы вызвались определенное количество раз
        //verify(javaQuestionService, times(amount)).getRandomQuestion();
        //verify(mathQuestionService, times(amount)).getRandomQuestion();
    }*/
  @Test
  public void getQuestionsTest() {
      List<Question> javaQuestions = new ArrayList<>();
      javaQuestions.add(new Question("Java Question 1", "Java Answer 1"));
      javaQuestions.add(new Question("Java Question 2", "Java Answer 2"));
      javaQuestions.add(new Question("Java Question 3", "Java Answer 3"));

      List<Question> mathQuestions = new ArrayList<>();
      mathQuestions.add(new Question("Math Question 1", "Math Answer 1"));
      mathQuestions.add(new Question("Math Question 2", "Math Answer 2"));
      mathQuestions.add(new Question("Math Question 3", "Math Answer 3"));

      when(javaQuestionService.getAllQuestions()).thenReturn(javaQuestions);
      when(mathQuestionService.getAllQuestions()).thenReturn(mathQuestions);
      when(javaQuestionService.getRandomQuestion())
              .thenReturn(javaQuestions.get(0))
              .thenReturn(javaQuestions.get(1))
              .thenReturn(javaQuestions.get(2));
      when(mathQuestionService.getRandomQuestion())
              .thenReturn(mathQuestions.get(0))
              .thenReturn(mathQuestions.get(1))
              .thenReturn(mathQuestions.get(2));

      int amount = 5;
      Collection<Question> questions = examinerService.getQuestions(amount);

      assertEquals(amount, questions.size());

      System.out.println(questions);
      System.out.println(javaQuestions);

      // Проверка на содержание вопросов из обоих тем
      assertTrue(CollectionUtils.containsAny(questions, javaQuestions));
      assertTrue(questions.stream().anyMatch(javaQuestions::contains));
      assertTrue(questions.stream().anyMatch(mathQuestions::contains));

      // Убедится что javaQuestionService.getRandomQuestion() and mathQuestionService.getRandomQuestion()
      // методы вызвались определенное количество раз

  }

    @Test
    void getQuestions_NotEnoughQuestionsAvailable(){
        int amount = 4;
        List<Question> javaQuestions = new ArrayList<>();
        javaQuestions.add(new Question("Java Question 1", "Java Answer 1"));
        javaQuestions.add(new Question("Java Question 2", "Java Answer 2"));

        List<Question> mathQuestions = new ArrayList<>();
        mathQuestions.add(new Question("Math Question 1", "Math Answer 1"));

        when(javaQuestionService.getAllQuestions()).thenReturn(javaQuestions);
        when(mathQuestionService.getAllQuestions()).thenReturn(mathQuestions);

        assertThrows(BadRequestException.class, () -> {
            examinerService.getQuestions(amount);
        });

        verify(javaQuestionService, never()).getRandomQuestion();
        verify(mathQuestionService, never()).getRandomQuestion();
    }
    //=============================================================================
    @Test
    public void testGetQuestions_EnoughQuestionsAvailable(){
        int amount = 5;
        List<Question> javaQuestions = new ArrayList<>();
        javaQuestions.add(new Question("Java Question 1", "Java Answer 1"));
        javaQuestions.add(new Question("Java Question 2", "Java Answer 2"));
        javaQuestions.add(new Question("Java Question 3", "Java Answer 3"));

        List<Question> mathQuestions = new ArrayList<>();
        mathQuestions.add(new Question("Math Question 1", "Math Answer 1"));
        mathQuestions.add(new Question("Math Question 2", "Math Answer 2"));
        mathQuestions.add(new Question("Math Question 3", "Math Answer 3"));

        when(javaQuestionService.getAllQuestions()).thenReturn(javaQuestions);
        when(mathQuestionService.getAllQuestions()).thenReturn(mathQuestions);
        when(javaQuestionService.getRandomQuestion())
                .thenReturn(javaQuestions.get(0))
                .thenReturn(javaQuestions.get(1))
                .thenReturn(javaQuestions.get(2));
        when(mathQuestionService.getRandomQuestion())
                .thenReturn(mathQuestions.get(0))
                .thenReturn(mathQuestions.get(1))
                .thenReturn(mathQuestions.get(2));

        Collection<Question> result = examinerService.getQuestions(amount);
        System.out.println(result);
        assertEquals(amount, result.size());
        assertEquals(5,result.size());

    }


    @Test
    public void testGetQuestions_NotEnoughQuestionsAvailable() {
        int amount = 11;

        Collection<Question> javaQuestions = createJavaQuestions();
        Collection<Question> mathQuestions = createMathQuestions();

        when(javaQuestionService.getAllQuestions()).thenReturn(javaQuestions);
        when(mathQuestionService.getAllQuestions()).thenReturn(mathQuestions);

        assertThrows(BadRequestException.class, () -> examinerService.getQuestions(amount));

        verify(javaQuestionService, never()).getRandomQuestion();
        verify(mathQuestionService, never()).getRandomQuestion();
    }
    //  коллекция вопросов для Java
    private Collection<Question> createJavaQuestions() {
        List<Question> javaQuestions = new ArrayList<>();
        javaQuestions.add(new Question("Java Question 1", "Java Answer 1"));
        javaQuestions.add(new Question("Java Question 2", "Java Answer 2"));
        javaQuestions.add(new Question("Java Question 3", "Java Answer 3"));
        javaQuestions.add(new Question("Java Question 4", "Java Answer 4"));
        javaQuestions.add(new Question("Java Question 5", "Java Answer 5"));


        return javaQuestions;
    }
    // коллекция вопросов для математики
    private Collection<Question> createMathQuestions() {
        List<Question> mathQuestions = new ArrayList<>();
        mathQuestions.add(new Question("Math Question 1", "Math Answer 1"));
        mathQuestions.add(new Question("Math Question 2", "Math Answer 2"));
        mathQuestions.add(new Question("Math Question 3", "Math Answer 3"));
        mathQuestions.add(new Question("Math Question 4", "Math Answer 4"));
        mathQuestions.add(new Question("Math Question 5", "Math Answer 5"));

        return mathQuestions;
    }
}