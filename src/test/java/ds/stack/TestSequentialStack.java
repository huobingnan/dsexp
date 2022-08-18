package ds.stack;

import ds.stack.impl.SequentialStack;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Test sequential stack")
public class TestSequentialStack extends TestIntStackBase {
    {
        stackSupplier = () -> {
            return new SequentialStack<>(20);
        };
    }
}
