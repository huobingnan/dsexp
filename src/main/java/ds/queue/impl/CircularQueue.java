package ds.queue.impl;

import ds.queue.IQueue;

@SuppressWarnings({"unchecked"})
public class CircularQueue<T> implements IQueue<T> {

    private final int capacity;
    private final Object[] array;
    private int front;
    private int rear;

    public CircularQueue(int cap) {
        if (cap < 0) throw new IllegalArgumentException("Capacity can't be negative number");
        capacity = cap;
        array = new Object[cap];
        front = 0; rear = 0;
    }

    @Override
    public void enqueue(T element) {
        // 判断队列是否已满
        final int newRearPos = (rear + 1) % capacity;
        if (newRearPos == front) throw new RuntimeException("Queue is full");
        array[rear] = element;
        rear = newRearPos;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) return null;
        final T result = (T) array[front];
        front = (front + 1) % capacity;
        return result;
    }

    @Override
    public boolean isEmpty() { return rear == front; }

    @Override
    public int size() { return (rear - front + capacity) % capacity; }

    @Override
    public T front() { return (T) array[front]; }
}
