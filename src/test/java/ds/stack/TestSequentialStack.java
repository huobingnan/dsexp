package ds.stack;

import ds.stack.impl.SequentialStack;

public class TestSequentialStack extends TestIntStackBase {
    {
        stackSupplier = () -> {
            return new SequentialStack<>(20);
        };
    }
}
