package com.example.kursovik2.service;

import com.example.kursovik2.model.Question;

import com.example.kursovik2.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("javaQuestionService")
public class JavaQuestionService implements QuestionService {
   private final QuestionRepository questionRepository;

    public JavaQuestionService(@Qualifier("javaQuestionRepository")QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void addQuestion(Question question) {
        questionRepository.add(question);
    }

    @Override
    public void removeQuestion(Question question) {
      questionRepository.remove(question);
    }

    public Collection<Question> getAllQuestions() {
        return Collections.unmodifiableCollection(questionRepository.getAll());

    }

    @Override
    public Question getRandomQuestion() {
        Collection<Question> questions = questionRepository.getAll();
        Random random = new Random();
        if (questions.isEmpty()){
            throw new RuntimeException("No question!");
        }
        int randomIndex = random.nextInt(questions.size());
        Iterator<Question> iterator = questions.iterator();
        // Перебираем элементы коллекции до нужного индекса
        for (int i = 0; i < randomIndex; i++) {
            iterator.next();
        }
        // Возвращаем вопрос по указанному индексу
        return iterator.next();
    }
}
