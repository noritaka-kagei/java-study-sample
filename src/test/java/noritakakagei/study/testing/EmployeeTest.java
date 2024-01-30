package noritakakagei.study.testing;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;

class EmployeeTest {

    private static Database database;
    private Employee employee;

    @BeforeAll
    static void setUpClass() {
        // データベース接続のシミュレーション
        database = new Database();
        database.connect();
    }

    @BeforeEach
    void setUp() {
        // Employee インスタンスの生成とデータベースへの登録
        employee = new Employee("John Doe", 25);    // test data
        database.insertEmployee(employee);
    }

    @Test
    @DisplayName("従業員が成人であることを確認")
    void testIsAdult() {
        boolean isAdult = employee.isAdult();
        assertThat(isAdult).isTrue();
    }

    @Test
    @DisplayName("従業員の名前が正しく取得されることを確認")
    void testName() {
        String name = employee.getName();
        assertThat(name).isEqualTo("John Doe");
    }

    @AfterEach
    void tearDown() {
        // データベースから従業員を削除
        database.deleteEmployee(employee);
    }

    @AfterAll
    static void tearDownClass() {
        // データベース接続の切断
        database.disconnect();
    }
}

class Database {
    void connect() { /* データベース接続のシミュレーション */ }
    void insertEmployee(Employee employee) { /* 従業員の登録 */ }
    void deleteEmployee(Employee employee) { /* 従業員の削除 */ }
    void disconnect() { /* データベース接続の切断 */ }
}
