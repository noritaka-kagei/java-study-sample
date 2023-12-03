package noritakakagei.study.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.assertj.core.api.Assertions.assertThat;


class CalcTest {
    private final Calc calc = new Calc();

    @BeforeAll
    void init() {
    }

    @Test
    void testAdd() {
        // simple assertion used by only JUnit5 package
        assertEquals(3, calc.add(1, 2));

        // more readable assertion used by AssertJ package supported JUnit5
        assertThat(calc.add(1, 2)).isEqualTo(3);
    }

    @Test
    void testSub() {
        assertThat(calc.sub(2,1)).isEqualTo(1);
    }
}
