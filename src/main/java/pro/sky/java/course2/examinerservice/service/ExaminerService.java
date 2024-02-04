package pro.sky.java.course2.examinerservice.service;

import pro.sky.java.course2.examinerservice.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestion(int numbersOfQuestions);
}
