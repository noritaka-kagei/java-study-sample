package noritakakagei.study.testing;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ColumTest {
    private final Colum colum = new Colum();

    @Test
    void testFailExam() {
        // assertThat(colum.passExam(59)).isFalse();
    }

    @Test
    void testPassExam() {
        assertThat(colum.passExam(60)).isTrue();
    }

    @Test
    void testFailExam2() {
        assertThat(colum.passExam2(59)).isFalse();
    }

    @Test
    void testPassExam2() {
        // assertThat(colum.passExam2(60)).isTrue();
    }
}