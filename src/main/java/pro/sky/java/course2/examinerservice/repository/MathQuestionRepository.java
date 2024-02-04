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
public class MathQuestionRepository implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();

    @Override
    @PostConstruct
    public void init() {
        questions.add(new Question("2+2= ", "4"));
        questions.add(new Question("5+5= ", "10"));
        questions.add(new Question("3+3= ", "6"));
        questions.add(new Question("8*8= ", "64"));
        questions.add(new Question("5*5= ", "100"));
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
