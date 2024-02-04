package pro.sky.java.course2.examinerservice.repository;

import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.exceptions.AlreadyExistException;
import pro.sky.java.course2.examinerservice.exceptions.NotFoundQuestionException;
import pro.sky.java.course2.examinerservice.model.Question;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class JavaQuestionRepositoryTest {
    QuestionRepository out = new JavaQuestionRepository();

    @Test
    void init() {
        out.init();
        assertThat(out.getAll()).containsExactlyInAnyOrder(
                new Question("Сколько бит занимает целое число типа byte", "8"),
                new Question("Сколько бит занимает целое число типа short", "16"),
                new Question("Сколько бит занимает целое число типа int", "32"),
                new Question("Сколько бит занимает целое число типа long", "64"),
                new Question("Сколько бит занимает число с плавающей точкой типа float", "32")
        );
    }


    @Test
    void addPositiveTest() {
        Question expected = new Question("Сколько бит занимает число с плавающей точкой типа double", "64");
        assertThat(out.add("Сколько бит занимает число с плавающей точкой типа double", "64")).isEqualTo(expected);
        assertThat(out.getAll()).contains(expected);
    }

    @Test
    void testAddObjPositiveTest() {
        assertThatNoException().isThrownBy(() -> out.add(new Question("Сколько бит занимает число с плавающей точкой типа double", "64")));
    }

    @Test
    void addNegativeTest() {
        out.add("Сколько бит занимает целое число типа byte", "8");
        assertThatExceptionOfType(AlreadyExistException.class).isThrownBy(() -> out.add("Сколько бит занимает целое число типа byte", "8"));
    }

    @Test
    void removePositiveTest() {
        Question expected = new Question("Сколько бит занимает целое число типа byte", "8");
        out.add("Сколько бит занимает целое число типа byte", "8");
        assertThat(out.getAll()).contains(expected);
        out.remove(new Question("Сколько бит занимает целое число типа byte", "8"));
        assertThat(out.getAll()).doesNotContain(expected);
    }

    @Test
    void removeNegativeTest() {
        assertThatExceptionOfType(NotFoundQuestionException.class).isThrownBy(() -> out.remove(new Question("Сколько бит занимает число с плавающей точкой типа double", "64")));
    }

    @Test
    void getAllPositiveTest() {
        out.add(new Question("Сколько бит занимает целое число типа byte", "8"));
        out.add(new Question("Сколько бит занимает целое число типа short", "16"));
        assertThat(out.getAll()).contains(
                new Question("Сколько бит занимает целое число типа byte", "8"),
                new Question("Сколько бит занимает целое число типа short", "16")
        );
    }
}