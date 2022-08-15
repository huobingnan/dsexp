package ds.list;

import static org.junit.jupiter.api.Assertions.*;

import static ds.test.util.TimeBenchmarkUtil.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;
import java.util.logging.Logger;

public class TestIntListBase {

    static final int NUMBER_OF_TEST_ELEMENT = 23;

    final Integer[] testElements = new Integer[] {
            1, 3, 100, 4, 5, 90, 7, 8, 9, 10, 11, 12,
            3212, 14, 15, 12, 17, 18, -20, 0, 21, -127, 23
    };

    protected Supplier<IList<Integer>> listSupplier;

    @BeforeEach
    public void beforeEachTestCase() {
        if (listSupplier == null)
            throw new IllegalStateException("You must initialize the listSupplier before test");
    }

    @Test
    public void test_list_push_back() {
        final IList<Integer> instance = listSupplier.get();
        System.out.println("==Test IList#pushBack ==");
        System.out.println("    implementation: " + instance.getClass().getName());
        timeIt(10000, () -> {
            for (int i = 0; i < NUMBER_OF_TEST_ELEMENT; i++) {
                instance.pushBack(testElements[i]);
            }
            assertEquals(NUMBER_OF_TEST_ELEMENT, instance.length());
            for (int i = 0; i < NUMBER_OF_TEST_ELEMENT; i++) {
                assertEquals(instance.at(i), testElements[i]);
            }
            assertThrowsExactly(RuntimeException.class, () -> {
                instance.at(-1);
            });
            assertThrowsExactly(RuntimeException.class, () -> {
                instance.at(instance.length());
            });
        });
        System.out.println("PASS!");
    }

    @Test
    public void test_list_head_insert() {
        final IList<Integer> instance = listSupplier.get();
        System.out.println("==Test IList#insert (head)==");
        System.out.println("    implementation: " + instance.getClass().getName());
        timeIt(() -> {
            for (int i = 0; i < NUMBER_OF_TEST_ELEMENT; i++) {
                instance.insert(0, testElements[i]);
            }
            assertEquals(instance.length(), NUMBER_OF_TEST_ELEMENT);
            for (int i = NUMBER_OF_TEST_ELEMENT-1; i >= 0; i--) {
                assertEquals(testElements[i], instance.at(NUMBER_OF_TEST_ELEMENT-i-1));
            }
        });
        System.out.println("PASS!");
    }

    @Test
    public void test_list_middle_insert() {

        final IList<Integer> instance = listSupplier.get();
        System.out.println("==Test IList#insert (middle)==");
        System.out.println("    implementation: " + instance.getClass().getName());
        timeIt(() -> {
            instance.pushBack(1); instance.pushBack(2);
            instance.pushBack(3); instance.pushBack(4);
            // expect: [1, 2, 3, 4]
            // insert into middle point
            instance.insert(1, 100); instance.insert(2, 101);
            // expect: [1, 100, 101, 2, 3, 4]
            assertEquals(6, instance.length());
            assertEquals(100, instance.at(1));
            assertEquals(101, instance.at(2));
        });
        System.out.println("PASS!");
    }

    @Test
    public void test_list_mixed_insert() {
        final IList<Integer> instance = listSupplier.get();
        System.out.println("==Test IList#insert (mixed)==");
        System.out.println("    implementation: " + instance.getClass().getName());
        timeIt(() -> {
            instance.pushBack(1); instance.pushBack(2);
            instance.pushBack(3); instance.pushBack(4);
            // expect: [1, 2, 3, 4]
            instance.insert(0, 100);
            // expect: [100, 1, 2, 3, 4]
            instance.insert(3, 100);
            // expect: [100, 1, 2, 100, 3, 4]
            instance.insert(2, 100);
            // expect: [100, 1, 100, 2, 100, 3, 4]
            instance.pushBack(100);
            // expect: [100, 1, 100, 2, 100, 3, 4, 100]
            final int[] expect = new int[]{100, 1, 100, 2, 100, 3, 4, 100};
            for (int i = 0; i < expect.length; i++) {
                assertEquals(expect[i], instance.at(i));
            }
        });
        System.out.println("PASS!");
    }

    @Test
    public void test_list_index_of() {
        final IList<Integer> instance = listSupplier.get();
        System.out.println("==Test IList#indexOf==");
        System.out.println("    implementation: " + instance.getClass().getName());
        timeIt(() -> {
            for (int i = 0; i < NUMBER_OF_TEST_ELEMENT; i++) {
                instance.pushBack(testElements[i]);
            }
            assertEquals(-1, instance.indexOf(0x342)); // 检查一个不存在的数
            assertEquals(2, instance.indexOf(100));
            assertEquals(11, instance.indexOf(12));
            assertEquals(22, instance.indexOf(23));
        });
        System.out.println("PASS!");
    }

    @Test
    public void test_list_head_delete() {
        final IList<Integer> instance = listSupplier.get();
        System.out.println("==Test IList#delete (head)==");
        System.out.println("    implementation: " + instance.getClass().getName());
        timeIt(() -> {
            for (int i = 0; i < NUMBER_OF_TEST_ELEMENT; i++)
                instance.pushBack(testElements[i]);
            for (int i = 0; i < NUMBER_OF_TEST_ELEMENT; i++) {
                assertEquals(testElements[i], instance.delete(0));
            }
        });
        System.out.println("PASS!");
    }

    @Test()
    public void test_list_mixed_delete() {
        final IList<Integer> instance = listSupplier.get();
        System.out.println("==Test IList#delete (mixed)==");
        System.out.println("    implementation: " + instance.getClass().getName());
        timeIt(() -> {
            for (int i = 0; i < NUMBER_OF_TEST_ELEMENT; i++) {
                instance.pushBack(testElements[i]);
            }
            // 头部删除
            assertEquals(1, instance.delete(0));
            assertEquals(3, instance.delete(0));
            assertEquals(100, instance.delete(0));
            // 中间部分删除
            assertEquals(90, instance.delete(2));
            assertEquals(10, instance.delete(5));
            assertEquals(11, instance.delete(5));
            // 尾部删除
            assertEquals(23, instance.delete(instance.length()-1));
            assertEquals(-127, instance.delete(instance.length()-1));
            // 查看表长
            assertEquals(NUMBER_OF_TEST_ELEMENT - 8, instance.length());
        });
        System.out.println("PASS!");
    }

    @Test
    public void test_list_set_item() {
        final IList<Integer> instance = listSupplier.get();
        System.out.println("==Test list#set==");
        System.out.println("    implementation: " + instance.getClass().getName());
        timeIt(() -> {
            for (int i = 0; i < NUMBER_OF_TEST_ELEMENT; i++) {
                instance.pushBack(testElements[i]);
            }
            assertThrowsExactly(RuntimeException.class, () -> {
                instance.set(-1, 12);
            });
            assertThrowsExactly(RuntimeException.class, () -> {
                instance.set(instance.length(), 11);
            });
            for (int i = 0, len = instance.length(); i < len; i++) {
                if (i % 2 == 0) instance.set(i, 0);
                else instance.set(i, 1);
            }
            for (int i = 0, len = instance.length(); i < len; i++) {
                if (i % 2 == 0) assertEquals(0, instance.at(i));
                else assertEquals(1, instance.at(i));
            }
        });
        System.out.println("PASS!");
    }
}
