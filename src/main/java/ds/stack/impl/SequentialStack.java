package ds.stack.impl;

import ds.annotation.Experimental;
import ds.stack.IStack;

/**
 * 顺序栈，容量是固定的
 * @param <T>
 */
@Experimental
@SuppressWarnings({"unchecked"})
public class SequentialStack<T> implements IStack<T> {
    public static final int DEFAULT_SIZE = 15;
    private Object[] elements;
    private int      length;   // length即表示了当前队列的长度，也表示下一个要插入元素的索引

    public SequentialStack() {
        elements = new Object[DEFAULT_SIZE];
        length   = 0;
    }

    public SequentialStack(int cap) {
        if (cap <= 0) throw new IllegalArgumentException();
        elements = new Object[cap];
        length   = 0;
    }

    @Override
    public int length() { return length; }

    @Override
    public boolean isEmpty() { return length == 0; }

    @Override
    public void push(T value) {
        if (length >= elements.length) throw new RuntimeException("Stack is full");
        elements[length++] = value;
    }

    @Override
    public T pop() {
        if (length <= 0) throw new RuntimeException("Stack is empty");
        return (T) elements[--length];
    }

    @Override
    public T top() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return (T) elements[length-1];
    }
}
