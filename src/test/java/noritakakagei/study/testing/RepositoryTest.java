package noritakakagei.study.testing;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// test target class
class Repository {
    public List<String> getManagers() {
        // execute query and create getting manager list(collection object)
        return null;
    }
}

// test class (test suite)
class RepositoryTest {
    private final Repository repository = new Repository();

    @BeforeAll
    void initDB() {
        // initialize database
        // ex. create table schema and insert test data
    }

    @Test
    void testGetManagers() {
        List<String> actual = repository.getManagers();
        assertThat(actual)
            .isNotNull()
            .hasSize(3)
            .containsExactly("managerA", "managerB", "managerC");
    }
}