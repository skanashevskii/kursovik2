package com.example.kursovik2.service;

import com.example.kursovik2.exception.BadRequestException;
import com.example.kursovik2.model.Question;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;


    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                               @Qualifier("mathQuestionService") QuestionService mathQuestionService) {

        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;

    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> questions = new ArrayList<>();

        Collection<Question> javaQuestions = new ArrayList<>(javaQuestionService.getAllQuestions());
        Collection<Question> mathQuestions = new ArrayList<>(mathQuestionService.getAllQuestions());

        if (javaQuestions.size() + mathQuestions.size() < amount) {
            throw new BadRequestException("Not enough questions available.");
        }

        Random random = new Random();
        int javaQuestionCount = random.nextInt(amount + 1);
        int mathQuestionCount = amount - javaQuestionCount;

        while (javaQuestionCount > 0) {
            if (!javaQuestions.isEmpty()) {
                Question randomJavaQuestion = javaQuestionService.getRandomQuestion();
                questions.add(randomJavaQuestion);
                javaQuestions.remove(randomJavaQuestion);
                javaQuestionCount--;
            } else {
                break;
            }
        }

        while (mathQuestionCount > 0) {
            if (!mathQuestions.isEmpty()) {
                Question randomMathQuestion = mathQuestionService.getRandomQuestion();
                questions.add(randomMathQuestion);
                mathQuestions.remove(randomMathQuestion);
                mathQuestionCount--;
            } else {
                break;
            }
        }

        return Collections.unmodifiableCollection(questions);
    }

}
