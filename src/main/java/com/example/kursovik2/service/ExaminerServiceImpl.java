package com.example.kursovik2.service;

import com.example.kursovik2.exception.BadRequestException;
import com.example.kursovik2.model.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class ExaminerServiceImpl implements ExaminerService{
    private Random random ;
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    @Autowired
    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                               @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.random = new Random();
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;

    }

    public List<Question> getRandomQuestions(int javaQuestionsCount, int mathQuestionsCount) {
        List<Question> randomQuestions = new ArrayList<>();

        Collection<Question> javaQuestions = javaQuestionService.getAllQuestions();
        Collection<Question> mathQuestions = mathQuestionService.getAllQuestions();

        List<Question> shuffledJavaQuestions=new ArrayList<>(javaQuestions);
        Collections.shuffle(shuffledJavaQuestions,random);
        List<Question> shuffledMathQuestions=new ArrayList<>(mathQuestions);
        Collections.shuffle(shuffledMathQuestions,random);

        int javaCount = Math.min(javaQuestionsCount, javaQuestions.size());
        int mathCount = Math.min(mathQuestionsCount, mathQuestions.size());

        randomQuestions.addAll(shuffledJavaQuestions.subList(0,javaCount));
        randomQuestions.addAll(shuffledMathQuestions.subList(0, mathCount));

        return randomQuestions;
    }
    public void generateRandomExam() {
        // Генерация случайного экзамена с вопросами из обоих сервисов

        List<Question> randomQuestions = getRandomQuestions(4, 3);

        // Далее выполняется логика по генерации экзамена на основе полученных случайных вопросов
    }

    public Collection<Question> getAllQuestions(int amount){
    Collection<Question> allQuestions = javaQuestionService.getAllQuestions();
    if (amount>allQuestions.size()){
            throw new BadRequestException("Количество запросов превышает количество доступных");
        }
        Set<Question>uniqueQuestions = new HashSet<>();
        List<Question> allQuestionsList = new ArrayList<>(allQuestions);
        while (uniqueQuestions.size()<amount){
            uniqueQuestions.add(allQuestionsList.get(random.nextInt(allQuestionsList.size())));
        }

        return Collections.unmodifiableCollection(uniqueQuestions);
    }

}
