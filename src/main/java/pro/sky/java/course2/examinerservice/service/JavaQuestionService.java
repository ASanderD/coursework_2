package pro.sky.java.course2.examinerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.model.Question;
import pro.sky.java.course2.examinerservice.repository.QuestionRepository;

import java.util.*;

@Service
@Component
public class JavaQuestionService implements QuestionService {
    private final Random random = new Random();

    private final QuestionRepository questionRepository;

    @Autowired
    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question add(String question, String answer) {
        return questionRepository.add(question, answer);
    }

    public Question add(Question question) {
        return questionRepository.add(question);
    }


    public Question remove(Question question) {
        return questionRepository.remove(question);
    }


    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questionList = new ArrayList<>(getAll());
        int numberOfQuestions = random.nextInt(getAll().size());
        return questionList.get(numberOfQuestions);
    }
}
