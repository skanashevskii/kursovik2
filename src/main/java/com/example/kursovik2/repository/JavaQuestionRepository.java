package com.example.kursovik2.repository;

import com.example.kursovik2.model.Question;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


import java.util.*;

@Repository
@Qualifier("javaQuestionRepository")
public class JavaQuestionRepository implements QuestionRepository {
    private  Set<Question> questionsList;

    public JavaQuestionRepository() {
        questionsList = new HashSet<>();
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
