package com.example.kursovik2;

import com.example.kursovik2.model.Question;
import com.example.kursovik2.repository.JavaQuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class JavaQuestionRepositoryTest {

    private JavaQuestionRepository javaQuestionRepository;

    @BeforeEach
    public void setUp() {
        javaQuestionRepository = new JavaQuestionRepository();
    }

    @Test
    public void testAdd() {
        Question question = new Question("What is Java?", "Motobike");
        javaQuestionRepository.add(question);

        Collection<Question> questions = javaQuestionRepository.getAll();
        Assertions.assertTrue(questions.contains(question));
    }

    @Test
    public void testRemove() {
        Question question = new Question("What is Java?", "Motobike");
        javaQuestionRepository.add(question);
        javaQuestionRepository.remove(question);

        Collection<Question> questions = javaQuestionRepository.getAll();
        Assertions.assertFalse(questions.contains(question));
    }

    @Test
    public void testGetAll() {
        Question question1 = new Question("Question 1","Answer 1");
        Question question2 = new Question("Question 2", "Answer 2");
        javaQuestionRepository.add(question1);
        javaQuestionRepository.add(question2);

        Collection<Question> questions = javaQuestionRepository.getAll();
        Assertions.assertEquals(2, questions.size());
        Assertions.assertTrue(questions.contains(question1));
        Assertions.assertTrue(questions.contains(question2));
    }
}
