package noritakakagei.study.testing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AnnotationOrderAdvancedTest {
    static int count = 0;

    @BeforeAll
    static void init() {
        System.out.printf("[%2d] Parent Class: @BeforeAll\n", count++);
    }

    @BeforeEach
    void setup() {
        System.out.printf("[%2d] Parent Class: @BeforeEach\n", count++);
    }

    @AfterEach
    void tearDown() {
        System.out.printf("[%2d] Parent Class: @AfterEach\n", count++);
    }

    @AfterAll
    static void cleanup() {
        System.out.printf("[%2d] Parent Class: @AfterAll\n", count++);
    }

    @Test
    void testSample() {
        System.out.printf("[%2d] Parent Class: @Test\n", count++);
    }

    @Test
    void testSample2() {
        System.out.printf("[%2d] Parent Class: @Test2\n", count++);
    }

    @Nested
    class NestedTest {
        @BeforeAll
        static void init() {
            System.out.printf("[%2d] Nested Class: @BeforeAll\n", count++);
        }
    
        @BeforeEach
        void setup() {
            System.out.printf("[%2d] Nested Class: @BeforeEach\n", count++);
        }
    
        @AfterEach
        void tearDown() {
            System.out.printf("[%2d] Nested Class: @AfterEach\n", count++);
        }
    
        @AfterAll
        static void cleanup() {
            System.out.printf("[%2d] Nested Class: @AfterAll\n", count++);
        }
    
        @Test
        void testSample() {
            System.out.printf("[%2d] Nested Class: @Test\n", count++);
        }    

        @Test
        void testSample2() {
            System.out.printf("[%2d] Nested Class: @Test2\n", count++);
        }    
    }
}