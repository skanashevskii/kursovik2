package com.example.kursovik2.repository;

import com.example.kursovik2.model.Question;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Qualifier("mathQuestionRepository")
public class MathQuestionRepository implements QuestionRepository {
    private final Set<Question> questions;

    public MathQuestionRepository() {
        this.questions = new HashSet<>();
    }

/*    @PostConstruct
    public void init() {
        Question question1 = new Question("2+2=?", "4");
        Question question2 = new Question("2*5=?", "10");
        Question question3 = new Question("2/2=?", "1");
        Question question4 = new Question("5-2=?", "3");
        add(question1);
        add(question2);
        add(question3);
        add(question4);
    }*/

    @Override
    public void add(Question question) {
        questions.add(question);
    }

    @Override
    public void remove(Question question) {
        questions.remove(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return List.copyOf(questions);
    }
}
