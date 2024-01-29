package pro.sky.java.course2.examinerservice.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.exceptions.ExceededNumberOfQuestionException;
import pro.sky.java.course2.examinerservice.model.Question;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestion(int amount) {
        if (amount > questionService.getAll().size()) {
            throw new ExceededNumberOfQuestionException();
        }
        Set<Question> questionsForExam = new HashSet<>();
        for (int i = 0; i < amount; i++) {
            while (questionsForExam.size() < amount) {
                questionsForExam.add(questionService.getRandomQuestion());
            }
        }
        return questionsForExam;
    }
}
