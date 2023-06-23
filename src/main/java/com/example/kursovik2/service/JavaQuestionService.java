package com.example.kursovik2.service;

import com.example.kursovik2.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions;
    private final Random random;

    public JavaQuestionService() {
        questions = new HashSet<>();
        random = new Random();
    }


    @Override
    public Question add(String question, String answer) {
        Question quest = new Question(question, answer);
        questions.add(quest);
        return quest;
    }
    @Override
    public Question remove(String question, String answer) {
        Question quest = new Question(question, answer);
        questions.remove(quest);
        return quest;
    }
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);

    }

    @Override
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
    }

}
