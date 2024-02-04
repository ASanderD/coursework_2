package pro.sky.java.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.model.Question;
import pro.sky.java.course2.examinerservice.repository.QuestionRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private MathQuestionService mathQuestionService;

    List<Question> questionsTest = List.of(new Question("Сколько бит занимает целое число типа byte", "8"),
            new Question("2+2= ", "4"),
            new Question("5+5= ", "10"),
            new Question("3+3= ", "6"),
            new Question("8*8= ", "64")
    );

    @BeforeEach
    public void beforeEach() {
        when(questionRepository.getAll()).thenReturn(questionsTest);
    }

    @Test
    void getRandomQuestion() {
        Question actual = mathQuestionService.getRandomQuestion();
        assertThat(actual).isIn(mathQuestionService.getAll());
    }
}