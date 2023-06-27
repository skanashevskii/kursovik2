package com.example.kursovik2.service;

import com.example.kursovik2.model.Question;

import java.util.Collection;



public interface QuestionService {
    void addQuestion(Question question);
    void removeQuestion(Question question);
   Collection<Question> getAllQuestions();
   Question getRandomQuestion();

}
