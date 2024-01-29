package pro.sky.java.course2.examinerservice.service;

import pro.sky.java.course2.examinerservice.exceptions.AlreadyExistException;
import pro.sky.java.course2.examinerservice.exceptions.NotFoundQuestionException;
import pro.sky.java.course2.examinerservice.model.Question;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

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

    @Override
    public Question getRandomQuestion() {
        List<Question> questionList = new ArrayList<>(questions);
        int numberOfQuestions = random.nextInt(questions.size());
        return questionList.get(numberOfQuestions);
    }
}
