package ds.queue;

import ds.queue.impl.CircularQueue;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class CircularQueueTest {

    @Test
    public void shouldGetActualSize() {
        final IQueue<String> strQue = new CircularQueue<>(10);
        assertEquals(0, strQue.size());
        strQue.enqueue("a"); strQue.enqueue("b");
        assertEquals(2, strQue.size());
        strQue.enqueue("c");
        assertEquals("a", strQue.dequeue());
        assertEquals(2, strQue.size());
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenQueueIsFull() {
        final IQueue<String> strQue = new CircularQueue<>(10); // 只能存九个元素

        assertThrowsExactly(RuntimeException.class, () -> {
            IntStream.range(0, 10).mapToObj(String::valueOf).forEach(strQue::enqueue);
        });

        assertEquals(9, strQue.size());

        IntStream.range(0, 9).mapToObj(String::valueOf).forEach(elem -> {
            assertEquals(elem, strQue.dequeue());
        });
    }
}
