package noritakakagei.study.testing;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.internal.Strings;
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
        if (keyword.isEmpty()) return list;

        list.add(new Product(100, "device1"));
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

    @ParameterizedTest(name = "For example, existing product(s) when searching by word: \"{0}\"")
    @ValueSource(strings = {"laptop", "phone", "tablet"})
    void searchWithValidKeywordShouldReturnProductList(String keyword) {
        List<Product> result = svc.search(keyword);
        assertThat(result).isNotEmpty();
    }

    @ParameterizedTest(name = "For example, non existing product when searching by word: \"{0}\"")
    @EmptySource
    void searchWithEmptyKeywordShouldReturnEmptyList(String keyword) {
        List<Product> result = svc.search(keyword);
        assertThat(result).isEmpty();
    }
}


/* EXAMPLE OF EXECUTING TEST CODE VIA CONSOLE LAUNCHER */

// PS D:\Users\noritaka-kagei\Development\tmp-java> ls
// 
// 
//     ディレクトリ: D:\Users\noritaka-kagei\Development\tmp-java
// 
// 
// Mode                 LastWriteTime         Length Name
// ----                 -------------         ------ ----
// -a----        2024/02/20     22:28        2680679 junit-platform-console-standalone-1.10.2.jar
// -a----        2024/02/20     23:36            458 Product.class
// -a----        2024/02/20     23:36            687 ProductService.class
// -a----        2024/02/20     23:36           1504 ProductServiceTest.class
// -a----        2024/02/20     23:44           2008 ProductServiceTest.java
// 
// 
// PS D:\Users\noritaka-kagei\Development\tmp-java> java -cp ".;C:\Users\noritaka-kagei\.m2\repository\org\assertj\assertj-core\3.24.2\assertj-core-3.24.2.jar;D:\Users\noritaka-kagei\Development\tmp-java\junit-platform-console-standalone-1.10.2.jar" org.junit.platform.console.ConsoleLauncher execute --scan-class-path
// 
// Thanks for using JUnit! Support its development at https://junit.org/sponsoring
// 
// .
// +-- JUnit Jupiter [OK]
// | '-- ProductServiceTest [OK]
// |   +-- searchWithEmptyKeywordShouldReturnEmptyList(String) [OK]
// |   | '-- For example, non existing product when searching by word: "" [OK]
// |   '-- searchWithValidKeywordShouldReturnProductList(String) [OK]
// |     +-- For example, existing product(s) when searching by word: "laptop" [OK]
// |     +-- For example, existing product(s) when searching by word: "phone" [OK]
// |     '-- For example, existing product(s) when searching by word: "tablet" [OK]
// +-- JUnit Vintage [OK]
// '-- JUnit Platform Suite [OK]
// 
// Test run finished after 104 ms
// [         6 containers found      ]
// [         0 containers skipped    ]
// [         6 containers started    ]
// [         0 containers aborted    ]
// [         6 containers successful ]
// [         0 containers failed     ]
// [         4 tests found           ]
// [         0 tests skipped         ]
// [         4 tests started         ]
// [         0 tests aborted         ]
// [         4 tests successful      ]
// [         0 tests failed          ]
// 
// PS D:\Users\noritaka-kagei\Development\tmp-java>