package com.example.kursovik2.service;

import com.example.kursovik2.exception.BadRequestException;
import com.example.kursovik2.exception.ServiceException;
import com.example.kursovik2.model.Question;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service

public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;


    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                               @Qualifier("mathQuestionService") QuestionService mathQuestionService) {

        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;

    }

    /**
     * Данный метод getQuestions(int amount) возвращает коллекцию случайных вопросов.
     * Вот объяснение его логики работы:
     *
     * Создается пустая коллекция questions, которая будет содержать выбранные случайные вопросы.
     * Вычисляется общее количество доступных вопросов путем сложения количества вопросов
     * из javaQuestionService и mathQuestionService.
     * Если оба javaQuestionService и mathQuestionService не содержат вопросов
     * (т.е., их списки вопросов пусты), генерируется исключение ServiceException с сообщением
     * "Сборник вопросов пустой!".
     * Если общее количество доступных вопросов меньше, чем требуемое amount, генерируется
     * исключение BadRequestException с сообщением "Не достаточно вопросов в сборнике!".
     * Создается список questionServices, в который добавляются javaQuestionService и
     * mathQuestionService, если они содержат вопросы.
     * Инициализируется генератор случайных чисел random (рандомизатор) с использованием
     * ThreadLocalRandom, который будет использоваться для выбора случайного индекса в списке
     * questionServices.
     * Запускается цикл, который продолжается до тех пор, пока коллекция questions не достигнет
     * требуемого количества amount вопросов.
     * В каждой итерации цикла генерируется случайное число nextInt, которое представляет индекс
     * случайно выбранного элемента из questionServices.
     * Используя индекс nextInt, выбирается случайный QuestionService из списка questionServices.
     * Выбирается случайный вопрос (randomQuestion) из выбранного QuestionService.
     * Выбранный вопрос добавляется в коллекцию questions.
     * Когда количество вопросов в коллекции questions достигает amount, цикл завершается.
     * Возвращается неизменяемая коллекция questions с выбранными случайными вопросами.
     * @param amount
     * @return
     * @throws BadRequestException
     * @throws ServiceException
     */
    @Override
    public Collection<Question> getQuestions(int amount) throws BadRequestException,ServiceException{
        Collection<Question> questions = new HashSet<>();
        int availableQuestions = javaQuestionService.getAllQuestions().size() + mathQuestionService.getAllQuestions().size();
        if (javaQuestionService.getAllQuestions().isEmpty() && mathQuestionService.getAllQuestions().isEmpty()) {
            throw new ServiceException("Сборник вопросов пустой!");
        }
        if (availableQuestions < amount) {
            throw new BadRequestException("Не достаточно вопросов в сборнике!");
        }
        List<QuestionService> questionServices = new ArrayList<>();

        if (!javaQuestionService.getAllQuestions().isEmpty()) {
            questionServices.add(javaQuestionService);
        }
        if (!mathQuestionService.getAllQuestions().isEmpty()) {
            questionServices.add(mathQuestionService);
        }
        //Random random = new Random();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        while (questions.size() < amount) {
            int nextInt = random.nextInt(questionServices.size());
            QuestionService randomService = questionServices.get(nextInt);
            Question randomQuestion = randomService.getRandomQuestion();
            questions.add(randomQuestion);
        }

        return Collections.unmodifiableCollection(questions);
    }
}

  /*  @Override
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
    }*/


