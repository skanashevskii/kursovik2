package com.example.kursovik2.controllers;

import com.example.kursovik2.model.Question;
import com.example.kursovik2.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final QuestionService questionService;

    @Autowired
    public JavaQuestionController(@Qualifier("javaQuestionService") QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/add")
    public void addQuestion(@RequestParam String question, @RequestParam String answer) {
        Question newQuestion = new Question(question,answer);
        questionService.addQuestion(newQuestion);
    }


    @GetMapping("/remove")
    public void removeQuestion(@RequestParam String question, @RequestParam String answer) {
        Question existingQuestion = new Question(question,answer);
        questionService.removeQuestion(existingQuestion);
    }


    @GetMapping
    public Collection<Question> get() {
        return questionService.getAllQuestions();
    }
}