package algo.list;

import ds.list.impl.SequentialList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCircuitLeftShift {
    final CircuitLeftShift shiftOp = new CircuitLeftShift(3);

    @Test
    public void test_case_1() {
        System.out.println("**Test sequential list circuit left shift**");
        final SequentialList<Integer> list =
                shiftOp.apply(new SequentialList<Integer>(new Object[]{1, 2, 3, 4, 5, 6, 7}));
        // expect: [4, 5, 6, 7, 1, 2, 3]
        final Integer[] expect = new Integer[]{ 4, 5, 6, 7, 1, 2, 3 };

        for (int i = 0; i < expect.length; i++) {
            assertEquals(expect[i], list.at(i));
        }
        System.out.println("PASS");
    }
}
