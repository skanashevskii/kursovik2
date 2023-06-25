package com.example.kursovik2.service;

import com.example.kursovik2.model.Question;
import com.example.kursovik2.repository.JavaQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("java")
public class JavaQuestionService implements QuestionService {
    private final JavaQuestionRepository javaQuestionRepository;

    @Autowired
    public JavaQuestionService(JavaQuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }


    //private final Random random;

 /*   public JavaQuestionService() {
        questions = new HashSet<>();
        random = new Random();
    }*/


    @Override
    public Question add(String question, String answer) {
        Question quest = new Question(question, answer);
        javaQuestionRepository.add(quest);
        return quest;
    }

    @Override
    public Question remove(String question, String answer) {
        Question quest = new Question(question, answer);
        javaQuestionRepository.remove(quest);
        return quest;
    }

    public List<Question> getAllQuestions() {
        return Collections.unmodifiableList(javaQuestionRepository.getAllQuestions());

    }

  /*  @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()){
            return null;
        }
        int limit = random.nextInt(questions.size());
        Iterator<Question> iterator = questions.iterator();
        for (int i = 0; i < limit; i++) {
            iterator.next();
        }

        return iterator.next();
    }*/

}
