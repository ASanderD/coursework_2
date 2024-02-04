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
class JavaQuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private JavaQuestionService javaQuestionService;

    List<Question> questionsTest = List.of(new Question("Сколько бит занимает целое число типа byte", "8"),
            new Question("Сколько бит занимает целое число типа short", "16"),
            new Question("Сколько бит занимает целое число типа int", "32"),
            new Question("Сколько бит занимает целое число типа long", "64"),
            new Question("Сколько бит занимает число с плавающей точкой типа float", "32")

    );

    @BeforeEach
    public void beforeEach() {
        when(questionRepository.getAll()).thenReturn(questionsTest);
    }

    @Test
    void getRandomQuestion() {
        assertThat(javaQuestionService.getRandomQuestion()).isIn(javaQuestionService.getAll());
    }

}