package noritakakagei.study.testing;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// test target class
class Calculator {
    public static int divide(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            throw new ArithmeticException("ゼロ除算が発生しました。");
        }
    }
}

// test class
class ExceptionTest {
    @Test
    void divide_ShouldThrowArithmeticException_WhenDivideIsZero() {
        // JUnit Style
        Exception exception = Assertions.assertThrows(ArithmeticException.class, () -> Calculator.divide(1, 0));
        Assertions.assertEquals("ゼロ除算が発生しました。", exception.getMessage());

        // AssertJ Style
        assertThatThrownBy(() -> Calculator.divide(1, 0))
            .isInstanceOf(ArithmeticException.class)
            .hasMessageContaining("ゼロ除算が発生しました。");
    }
}