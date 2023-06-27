package com.example.kursovik2.service;

import com.example.kursovik2.model.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("math")
public class MathQuestionService implements QuestionService {
    private final List<Question> questions;

    public MathQuestionService() {
        questions =new ArrayList<>();
    }


    @Override
    public Question add(String question, String answer) {
        Question quest = new Question(question, answer);
        questions.add(quest);
        return quest;
    }

    @Override
    public Question remove(String question, String answer) {
        Question quest = new Question(question, answer);
        questions.remove(quest);
        return quest;
    }

    public List<Question> getAllQuestions() {
        return Collections.unmodifiableList(questions);

    }
    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }

        int maxIndex = questions.size();
        int randomIndex = (int) (Math.random() * maxIndex);
        return questions.get(randomIndex);

    }
}

