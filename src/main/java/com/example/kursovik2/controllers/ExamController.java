package com.example.kursovik2.controllers;

import com.example.kursovik2.exception.BadRequestException;
import com.example.kursovik2.exception.ServiceException;
import com.example.kursovik2.model.Question;
import com.example.kursovik2.service.ExaminerService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.ServiceNotFoundException;
import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {

        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")

    public Collection<Question> getQuestions(@PathVariable int amount) throws BadRequestException,ServiceException{
        return examinerService.getQuestions(amount);
    }

}
