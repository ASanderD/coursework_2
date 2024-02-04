package pro.sky.java.course2.examinerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.exceptions.ExceededNumberOfQuestionException;
import pro.sky.java.course2.examinerservice.model.Question;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService javaQuestionService;

    private final QuestionService mathQuestionService;

    @Autowired
    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService, @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

       @Override
    public Collection<Question> getQuestion(int numbersOfQuestions) {
        if (numbersOfQuestions > (javaQuestionService.getAll().size() + mathQuestionService.getAll().size())) {
            throw new ExceededNumberOfQuestionException();
        }
        Set<Question> questionsForExam = new HashSet<>();
            while (questionsForExam.size() < numbersOfQuestions) {
                questionsForExam.add(javaQuestionService.getRandomQuestion());
                if (questionsForExam.size() < numbersOfQuestions) {
                    questionsForExam.add(mathQuestionService.getRandomQuestion());
                }
            }

        return questionsForExam;
    }
}
