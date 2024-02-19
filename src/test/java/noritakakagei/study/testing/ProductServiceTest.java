package noritakakagei.study.testing;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

class Product {
    private final int price;
    private final String name;

    Product(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

class ProductService {
    public List<Product> search(String keyword) {
        List<Product> list = new ArrayList<>();
        // add Product that matched keyword
        return list;
    }

    public void close() {
        // clean up for free resource
    }
}

/* TEST CLASS */
class ProductServiceTest {
    private ProductService svc;

    @BeforeEach
    void setUp() {
        svc = new ProductService();
    }

    @AfterEach
    void tearDown() {
        if (svc != null) {
            svc.close();
        }
    }

    @ParameterizedTest(name = "For example, existing product(s) when searching by word:{0}")
    @ValueSource(strings = {"laptop", "phone", "tablet"})
    void searchWithValidKeywordShouldReturnProductList(String keyword) {
        List<Product> results = svc.search(keyword);
        assertThat(results).isNotEmpty();
    }

    @ParameterizedTest(name = "For example, non existing product when searching by word:{0}")
    @EmptySource
    void searchWithEmptyKeywordShouldReturnEmptyList(String keyword) {
        List<Product> results = svc.search(keyword);
        assertThat(results).isEmpty();
    }
}