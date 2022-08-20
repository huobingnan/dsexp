package ds.list;

import ds.annotation.Specific;
import ds.list.impl.SequentialList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSequentialList extends TestIntListBase {

    {
        listSupplier = SequentialList::new;
    }

    @Test
    public void test_remove_if_equals_op() {
        System.out.println("**Test SequentialList#removeIf (equals)**");
        final SequentialList<Integer> list = new SequentialList<>(new Object[]{6, 1, 2, 5, 0, 6, 3, 6});
        list.removeIf(Integer.valueOf(6)::equals);
        assertEquals(5, list.length());
        for (int i = 0; i < list.length(); i++) assertNotEquals(6, list.at(i));
        System.out.println("PASS!");
    }


    @Test
    public void test_remove_if_range_op() {
        System.out.println("**Test SequentialList#removeIf (range)**");
        final SequentialList<Integer> list = new SequentialList<>(new Object[]{1, 2, 3, 5, 6, 7, 8, 9});
        // 删除范围在[3, 6]之间的元素
        list.removeIf(it -> it >= 3 && it <= 6);
        assertEquals(5, list.length());
        for (int i = 0; i < list.length(); i++) assertTrue(list.at(i) > 6 || list.at(i) < 3);
        System.out.println("PASS!");
    }

}
