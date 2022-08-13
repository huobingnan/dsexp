package ds.list;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;
import java.util.logging.Logger;

public class TestIntListBase {

    static final int NUMBER_OF_TEST_ELEMENT = 23;
    final Integer[] testElements = new Integer[] {
            1, 3, 100, 4, 5, 90, 7, 8, 9, 10, 11, 12,
            3212, 14, 15, 12, 17, 18, -20, 0, 21, -127, 23
    };

    private IList<Integer> instance;
    protected Supplier<IList<Integer>> listSupplier;

    @BeforeEach
    public void beforeEachTestCase() { instance = listSupplier.get(); }

    @Test
    public void test_list_push_back() {
        System.out.println("==Test list push back==");
        System.out.println("    instance: " + instance.getClass().getName());
        for (int i = 0; i < NUMBER_OF_TEST_ELEMENT; i++) {
            instance.pushBack(testElements[i]);
        }
        assertEquals(NUMBER_OF_TEST_ELEMENT, instance.length());
        for (int i = 0; i < NUMBER_OF_TEST_ELEMENT; i++) {
            assertEquals(instance.at(i), testElements[i]);
        }
        System.out.println("PASS!");
    }

    @Test
    public void test_list_head_insert() {
        System.out.println("==Test list head insert==");
        System.out.println("    instance: " + instance.getClass().getName());
        for (int i = 0; i < NUMBER_OF_TEST_ELEMENT; i++) {
            instance.insert(0, testElements[i]);
        }
        assertEquals(instance.length(), NUMBER_OF_TEST_ELEMENT);
        for (int i = NUMBER_OF_TEST_ELEMENT-1; i >= 0; i--) {
            assertEquals(testElements[i], instance.at(NUMBER_OF_TEST_ELEMENT-i-1));
        }
        System.out.println("PASS!");
    }

    @Test
    public void test_list_middle_insert() {

    }

    @Test
    public void test_list_index_of() {
        System.out.println("==Test list index of==");
        System.out.println("    instance: " + instance.getClass().getName());
        for (int i = 0; i < NUMBER_OF_TEST_ELEMENT; i++) {
            instance.pushBack(testElements[i]);
        }
        assertEquals(-1, instance.indexOf(0x342));
        assertEquals(2, instance.indexOf(100));
        assertEquals(11, instance.indexOf(12));
        assertEquals(22, instance.indexOf(23));
        System.out.println("PASS!");
    }

    @Test
    public void test_list_delete() {

    }
}
