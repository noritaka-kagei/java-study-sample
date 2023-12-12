package noritakakagei.study.testing;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// test target class
class Repository {
    public List<String> getManagers() {
        // execute query and create getting manager list(collection object)

        /* create sample data instead of actual data */
        List<String> result = new ArrayList<>();
        result.add("managerA");
        result.add("managerB");
        result.add("managerC");

        return result;
    }
}

// test class (test suite)
class RepositoryTest {
    private final Repository repository = new Repository();

    // @BeforeAll
    // void initDB() {
    //     // initialize database
    //     // ex. create table schema and insert test data
    // }

    @Test
    void testGetManagers() {
        List<String> managers = repository.getManagers();
        assertThat(managers)
            .isNotNull()
            .hasSize(3)
            .containsExactly("managerA", "managerB", "managerC");
    }
}