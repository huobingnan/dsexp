package ds.algo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Function;

@DisplayName("Test bracket match via linked stack")
public class TestBracketMatch {

    @Test
    @DisplayName("test all case")
    public void test_all_case() {
        final Function<String, Boolean> f = new BracketMatch();
        assertEquals(Boolean.TRUE,  f.apply("()"));
        assertEquals(Boolean.TRUE,  f.apply("[]"));
        assertEquals(Boolean.TRUE,  f.apply("{}"));
        assertEquals(Boolean.TRUE,  f.apply("[(), {}, {(), []}, {, () , [[[]]]}]"));
        assertEquals(Boolean.FALSE, f.apply("[[[[[[]]]]]"));
        assertEquals(Boolean.FALSE, f.apply("{{{{]]]}}}}"));
        assertEquals(Boolean.FALSE, f.apply("(((((]]][]}}}}]"));

        assertEquals(Boolean.TRUE,  f.apply("asdasfasferwqweasdda"));
        assertEquals(Boolean.TRUE,  f.apply(""));
        assertEquals(Boolean.TRUE,  f.apply(null));
    }
}
