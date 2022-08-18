package ds.stack.impl;

import ds.stack.IStack;

import java.io.Serializable;

/**
 * 链式栈实现，链式栈不存在上溢的情况
 * @param <T>
 */
public class LinkedStack<T> implements IStack<T> {
    private Node<T> top;    // 链式栈的头结点的引用
    private int     length; // 链式栈的长度

    @Override
    public int length() { return length; }

    @Override
    public boolean isEmpty() { return length == 0; }

    @Override
    public void push(T value) {
        final Node<T> node = new Node<>(value, null);
        if (top != null) top.next = node;
        top = node; length++;
    }

    @Override
    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        final T resVal = top.value;
        top = top.next; length--;
        return resVal;
    }

    @Override
    public T top() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return top.value;
    }

    private final class Node<T> implements Serializable {
        T       value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
