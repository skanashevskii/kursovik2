package com.example.kursovik2.repository;

import com.example.kursovik2.model.Question;

import java.util.List;

public interface QuestionRepository {
    void add(Question question);
    void remove(Question question);
    List<Question> getAllQuestions();
}
