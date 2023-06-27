package com.example.kursovik2.service;

import com.example.kursovik2.exception.BadRequestException;
import com.example.kursovik2.model.Question;

import java.util.Collection;


public interface ExaminerService {
     Collection<Question> getQuestions(int amount);

}
