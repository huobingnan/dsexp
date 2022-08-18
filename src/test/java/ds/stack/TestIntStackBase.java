package ds.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static ds.test.util.TimeBenchmarkUtil.*;

import java.util.Objects;
import java.util.function.Supplier;

@DisplayName("Test Integer Stack")
public class TestIntStackBase {
    public Supplier<IStack<Integer>> stackSupplier;

    private final Integer[] testElements = new Integer[] {
            12, 23, 1, 0, -21, 100, 9838, 0x4567, 9, 120
    };

    @BeforeEach
    public void beforeEachTestCase() { Objects.requireNonNull(stackSupplier); }

    @Test
    @DisplayName("test stack push")
    public void test_stack_push() {
        final IStack<Integer> stack = stackSupplier.get();
        System.out.println("==Test IStack#push==");
        System.out.println("    implementation: " + stack.getClass().getName());
        timeIt(() -> {
            for (int i = 0; i < testElements.length; i++) stack.push(testElements[i]);
        });
        assertEquals(testElements.length, stack.length());
        System.out.println("PASS!");
    }

    @Test
    @DisplayName("test stack pop")
    public void test_stack_pop() {
        final IStack<Integer> stack = stackSupplier.get();
        System.out.println("==Test IStack#pop==");
        System.out.println("    implementation: " + stack.getClass().getName());
        for (int i = 0; i < testElements.length; i++) stack.push(testElements[i]);
        timeIt(() -> {
            for (int i = testElements.length-1; i >= 0; i--)
                assertEquals(testElements[i], stack.pop());
        });
        System.out.println("PASS!");
    }

    @Test
    @DisplayName("test push and pop")
    public void test_push_and_pop() {
        final IStack<Integer> stack = stackSupplier.get();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(1, stack.pop());
        for (int i = 0; i < testElements.length; i++) stack.push(testElements[i]);
        for (int i = testElements.length-1; i >= 0; i--) assertEquals(testElements[i], stack.pop());
    }
}
