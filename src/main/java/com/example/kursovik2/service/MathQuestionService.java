package com.example.kursovik2.service;

import com.example.kursovik2.model.Question;
import com.example.kursovik2.repository.MathQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("math")
public class MathQuestionService implements QuestionService {
    private final List<Question> questions;

    @Autowired
    public MathQuestionService(List<Question> questions) {
        this.questions =questions;
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
        questions.removeIf(q -> q.getQuestion().equals(quest));
        return quest;
    }

    public Collection<Question> getAllQuestions() {
        return Collections.unmodifiableCollection(questions);

    }
    @Override
    public Question getRandomQuestion() {
        int maxIndex = questions.size();
        int randomIndex = (int) (Math.random() * maxIndex);
        return questions.get(randomIndex);
    }
}

