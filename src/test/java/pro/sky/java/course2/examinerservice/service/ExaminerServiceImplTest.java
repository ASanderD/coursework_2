package pro.sky.java.course2.examinerservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.exceptions.ExceededNumberOfQuestionException;
import pro.sky.java.course2.examinerservice.model.Question;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    private final Random random = new Random();

    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerServiceImpl;
    @BeforeEach
    public void beforeEach() {
        when(questionService.getAll()).thenReturn(questionsTest);
    }

    Collection<Question> questionsTest = List.of(new Question("Сколько бит занимает целое число типа byte", "8"),
            new Question("Сколько бит занимает целое число типа short", "16"),
            new Question("Сколько бит занимает целое число типа int", "32"),
            new Question("Сколько бит занимает целое число типа long", "64"),
            new Question("Сколько бит занимает число с плавающей точкой типа float", "32"));

    @Test
    void getQuestionPositiveTest() {
        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Сколько бит занимает целое число типа short", "16"),
                new Question("Сколько бит занимает целое число типа short", "16"),
                new Question("Сколько бит занимает целое число типа short", "16"),
                new Question("Сколько бит занимает целое число типа int", "32"),
                new Question("Сколько бит занимает целое число типа long", "64"),
                new Question("Сколько бит занимает целое число типа long", "64"),
                new Question("Сколько бит занимает число с плавающей точкой типа float", "32")
        );
        assertThat(examinerServiceImpl.getQuestion(4)).containsExactlyInAnyOrder(
                new Question("Сколько бит занимает целое число типа short", "16"),
                new Question("Сколько бит занимает целое число типа int", "32"),
                new Question("Сколько бит занимает целое число типа long", "64"),
                new Question("Сколько бит занимает число с плавающей точкой типа float", "32")
        );

    }
    @Test
    void getQuestionNegativeTest() {
        assertThatExceptionOfType(ExceededNumberOfQuestionException.class).isThrownBy(()-> examinerServiceImpl.getQuestion(questionsTest.size()+1));

    }
}