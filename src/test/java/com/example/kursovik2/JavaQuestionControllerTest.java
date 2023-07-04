package com.example.kursovik2;

import com.example.kursovik2.controllers.JavaQuestionController;
import com.example.kursovik2.model.Question;
import com.example.kursovik2.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QuestionService questionService;

    private JavaQuestionController javaQuestionController;

    @BeforeEach
    public void setUp() {
        javaQuestionController = new JavaQuestionController(questionService);
        mockMvc = MockMvcBuilders.standaloneSetup(javaQuestionController).build();
    }

    @Test
    public void testAddQuestion() throws Exception {
        Question question = new Question("What is Java?", "Java is a programming language.");
        doNothing().when(questionService).addQuestion(any(Question.class));

        mockMvc.perform(get("/exam/java/add")
                        .param("question", question.getQuestion())
                        .param("answer", question.getAnswer())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.question").value(question.getQuestion()))
                .andExpect(jsonPath("$.answer").value(question.getAnswer()));

        verify(questionService, times(1)).addQuestion(any(Question.class));
    }

    @Test
    public void testRemoveQuestion() throws Exception {
        Question question = new Question("What is Java?", "Java is a programming language.");

        mockMvc.perform(get("/exam/java/remove")
                        .param("question", question.getQuestion())
                        .param("answer", question.getAnswer())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.question").value(question.getQuestion()))
                .andExpect(jsonPath("$.answer").value(question.getAnswer()));

        verify(questionService, times(1)).removeQuestion(any(Question.class));
    }

    @Test
    public void testGet() throws Exception {
        Question question1 = new Question("Question 1", "Answer 1");
        Question question2 = new Question("Question 2", "Answer 2");
        Collection<Question> questions = Arrays.asList(question1, question2);

        when(questionService.getAllQuestions()).thenReturn(questions);

        mockMvc.perform(get("/exam/java")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].question").value(question1.getQuestion()))
                .andExpect(jsonPath("$[0].answer").value(question1.getAnswer()))
                .andExpect(jsonPath("$[1].question").value(question2.getQuestion()))
                .andExpect(jsonPath("$[1].answer").value(question2.getAnswer()));

        verify(questionService, times(1)).getAllQuestions();
    }
}
