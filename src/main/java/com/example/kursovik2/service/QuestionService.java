package com.example.kursovik2.service;

import com.example.kursovik2.model.Question;

import java.util.Collection;



public interface QuestionService {
    Question add(String question, String answer);
    //Question add(Question question);
    Question remove(String question, String answer);
   Collection<Question> getAllQuestions();

}
