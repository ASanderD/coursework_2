package pro.sky.java.course2.examinerservice.repository;

import jakarta.annotation.PostConstruct;
import pro.sky.java.course2.examinerservice.model.Question;

import java.util.Collection;

public interface QuestionRepository {
    @PostConstruct
    void init();

    Question add(String question, String answer);

    Question add(Question question);


    Question remove(Question question);

    Collection<Question> getAll();

}
