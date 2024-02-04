package pro.sky.java.course2.examinerservice.repository;

import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.exceptions.AlreadyExistException;
import pro.sky.java.course2.examinerservice.exceptions.NotFoundQuestionException;
import pro.sky.java.course2.examinerservice.model.Question;

import static org.assertj.core.api.Assertions.*;

class MathQuestionRepositoryTest {
    QuestionRepository out = new MathQuestionRepository();


    @Test
    void init() {
        out.init();
        assertThat(out.getAll()).containsExactlyInAnyOrder(
                new Question("2+2= ", "4"),
                new Question("5+5= ", "10"),
                new Question("3+3= ", "6"),
                new Question("8*8= ", "64"),
                new Question("5*5= ", "100")
        );
    }


    @Test
    void addPositiveTest() {
        Question expected = new Question("2+2= ", "4");
        assertThat(out.add("2+2= ", "4")).isEqualTo(expected);
        assertThat(out.getAll()).contains(expected);
    }

    @Test
    void testAddObjPositiveTest() {
        assertThatNoException().isThrownBy(() -> out.add(new Question("2+2= ", "4")));
    }

    @Test
    void addNegativeTest() {
        out.add("2+2= ", "4");
        assertThatExceptionOfType(AlreadyExistException.class).isThrownBy(() -> out.add("2+2= ", "4"));
    }

    @Test
    void removePositiveTest() {
        Question expected = new Question("2+2= ", "4");
        out.add("2+2= ", "4");
        assertThat(out.getAll()).contains(expected);
        out.remove(new Question("2+2= ", "4"));
        assertThat(out.getAll()).doesNotContain(expected);

    }

    @Test
    void removeNegativeTest() {
        assertThatExceptionOfType(NotFoundQuestionException.class).isThrownBy(() -> out.remove(new Question("2+2= ", "4")));
    }

    @Test
    void getAllPositiveTest() {
        out.add(new Question("2+2= ", "4"));
        out.add(new Question("5+5= ", "10"));
        out.add(new Question("3+3= ", "6"));
        assertThat(out.getAll()).contains(
                new Question("2+2= ", "4"),
                new Question("5+5= ", "10"),
                new Question("3+3= ", "6")
                );
    }
}