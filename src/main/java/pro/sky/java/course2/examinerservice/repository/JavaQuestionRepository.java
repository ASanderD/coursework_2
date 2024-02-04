package pro.sky.java.course2.examinerservice.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pro.sky.java.course2.examinerservice.exceptions.AlreadyExistException;
import pro.sky.java.course2.examinerservice.exceptions.NotFoundQuestionException;
import pro.sky.java.course2.examinerservice.model.Question;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
@Component
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();

    @PostConstruct
    public void init() {
        questions.add(new Question("Сколько бит занимает целое число типа byte", "8"));
        questions.add(new Question("Сколько бит занимает целое число типа short", "16"));
        questions.add(new Question("Сколько бит занимает целое число типа int", "32"));
        questions.add(new Question("Сколько бит занимает целое число типа long", "64"));
        questions.add(new Question("Сколько бит занимает число с плавающей точкой типа float", "32"));
    }

    @Override
    public Question add(String question, String answer) {

        Question question1 = new Question(question, answer);
        return add(question1);
    }

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new AlreadyExistException();
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new NotFoundQuestionException();
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }
}

