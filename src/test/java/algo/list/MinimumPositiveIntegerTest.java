package algo.list;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Function;

public class MinimumPositiveIntegerTest {

    final Function<int[], Integer> solution = new MinimumPositiveInteger();

    @Test
    public void shouldReturnOneAsResult() {
        assertEquals(1, solution.apply(new int[] {-5, 3, 2, 3}));
    }

    @Test
    public void shouldReturnFourAsResult() {
        assertEquals(4, solution.apply(new int[]{1, 2, 3}));
    }

    @Test
    public void shouldReturnTwoAsResult() {
        assertEquals(2, solution.apply(new int[]{-5, 1, 1, 1, 1, 1, 1}));
    }

}
