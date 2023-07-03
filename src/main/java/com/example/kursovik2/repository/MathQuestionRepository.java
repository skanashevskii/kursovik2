package com.example.kursovik2.repository;

import com.example.kursovik2.model.Question;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("mathQuestionRepository")
public class MathQuestionRepository implements QuestionRepository {
    private final Set<Question> questionsList;

    public MathQuestionRepository() {
        this.questionsList = new HashSet<>();
    }

    @Override
    public void add(Question question) {
        questionsList.add(question);
    }

    @Override
    public void remove(Question question) {
        questionsList.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questionsList);
    }
}
