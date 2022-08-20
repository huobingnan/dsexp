package algo.list;

import ds.list.impl.SequentialList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试顺序表的删除算法
 */
@DisplayName("Test sequential list #removeIf")
public class TestSequentialListRemoveIf {

    @Test
    public void test_case_1() {
        final SequentialList<Integer> list = new SequentialList<>();
        list.pushBack(1); list.pushBack(2); list.pushBack(2);
        list.pushBack(0); list.pushBack(3); list.pushBack(2);
        list.removeIf(Integer.valueOf(2)::equals);
        assertEquals(3, list.length());
        for (int i = 0; i < list.length(); i++) assertNotEquals(2, list.at(i));
    }

    @Test
    public void test_remove_even_number() {
        final SequentialList<Integer> list = new SequentialList<>(new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        list.removeIf(it -> it % 2 == 0); // 删除所有的偶数
        assertEquals(5, list.length());
        for (int i = 0; i < list.length(); i++) assertNotEquals(0, list.at(i) % 2);
    }
}
