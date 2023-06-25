package com.example.kursovik2.repository;

import com.example.kursovik2.model.Question;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository

public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();

    //JavaQuestionRepository(){}
    @PostConstruct
    public void init() {
        Question question1 = new Question("Где?", "Везде");
        Question question2 = new Question("Почему?", "Потому");
        Question question3 = new Question("Сколько?", "Много");
        Question question4 = new Question("Как?", "Молча");
        add(question1);
        add(question2);
        add(question3);
        add(question4);
    }

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
