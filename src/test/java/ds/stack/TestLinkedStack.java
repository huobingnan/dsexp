package ds.stack;

import ds.stack.impl.LinkedStack;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Test linked stack")
public class TestLinkedStack extends TestIntStackBase {
    {
        stackSupplier = LinkedStack::new;
    }
}
