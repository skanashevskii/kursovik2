package com.example.kursovik2.service;

import com.example.kursovik2.exception.BadRequestException;
import com.example.kursovik2.model.Question;

import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class ExaminerServiceImpl implements ExaminerService{
    private Random random ;

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    public Collection<Question> getQuestions(int amount){
        if (amount>questionService.getAll().size()){
            throw new BadRequestException("Количество запросов превышает количество доступных");
        }
        Set<Question> uniqueQuestions = new HashSet<>();
        while (uniqueQuestions.size()<amount){
            uniqueQuestions.add(questionService.getRandomQuestion());
        }

        return new ArrayList<>(uniqueQuestions);
    }
}
