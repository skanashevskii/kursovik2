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
    private final MathQuestionRepository mathQuestionRepository;

    @Autowired
    public MathQuestionService(MathQuestionRepository mathQuestionRepository) {
        this.mathQuestionRepository = mathQuestionRepository;
    }


    @Override
    public Question add(String question, String answer) {
        Question quest = new Question(question, answer);
        mathQuestionRepository.add(quest);
        return quest;
    }

    @Override
    public Question remove(String question, String answer) {
        Question quest = new Question(question, answer);
        mathQuestionRepository.remove(quest);
        return quest;
    }

    public Collection<Question> getAllQuestions() {
        return Collections.unmodifiableCollection(mathQuestionRepository.getAllQuestions());

    }
}

