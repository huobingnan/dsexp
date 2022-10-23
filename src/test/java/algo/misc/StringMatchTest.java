package algo.misc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringMatchTest {

    @Test
    public void bfTestCase() {
        int res = StringMatch.bf("abcacaf", "acaf");
        assertEquals(3, res);
        res = StringMatch.bf("abcd", "efghijk");
        assertEquals(-1, res);
        res = StringMatch.bf("a", "abcd");
        assertEquals(-1, res);
        res = StringMatch.bf("", "abcd");
        assertEquals(-1, res);
        res = StringMatch.bf("abcd", "");
        assertEquals(0, res);
        res = StringMatch.bf("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbf", "bf");
        assertEquals(57, res);
    }

}
