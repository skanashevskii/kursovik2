package com.example.kursovik2.controllers;

import com.example.kursovik2.model.Question;
import com.example.kursovik2.service.QuestionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping("/exam/java")
public class JavaController {
    private final QuestionService service;

    public JavaController(QuestionService service) {
        this.service = service;
    }


    @GetMapping("/add")
    public Question add(@RequestParam String question, @RequestParam String answer) {
        return service.add(question, answer);
    }



    @GetMapping("/remove")
    public Question remove(@RequestParam String question, @RequestParam String answer) {
        return service.remove(question,answer);
    }



    @GetMapping
    public Collection<Question> get() {
        return service.getAll();
    }
}
