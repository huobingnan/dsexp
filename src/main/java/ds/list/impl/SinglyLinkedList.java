package ds.list.impl;

import ds.annotation.Experimental;
import ds.list.AbstractList;

import java.util.Objects;

/**
 * 带有头结点的单链表实现
 * @param <T>
 */
@Experimental
public class SinglyLinkedList<T> extends AbstractList<T> {

    private Node<T> headNode;   // 头结点
    private int     length; // 记录表长

    public SinglyLinkedList() { headNode = new Node<>(null, null); length = 0; }

    @Override
    public boolean isEmpty() { return length == 0; }

    @Override
    public int length() { return length; }

    @Override
    public void insert(int idx, T value) {
        if (idx < 0) throw new RuntimeException("Index out of bound");
        Node<T> ptr = headNode; int count = 0;
        while (ptr != null && count < idx) {
            ptr = ptr.next; count++;
        }
        if (ptr == null) throw new RuntimeException("Index out of bound");
        ptr.next = new Node<>(ptr.next, value);
        length++;
    }

    @Override
    public void pushBack(T value) { insert(length, value); }

    @Override
    public T at(int idx) {
        if (idx < 0 || idx >= length) throw new RuntimeException("Index of bound");
        Node<T> ptr = headNode;
        for (int i = 0; i <= idx; i++) ptr = ptr.next;
        return ptr.value;
    }

    @Override
    public void set(int idx, T value) {
        if (idx < 0 || idx >= length) throw new RuntimeException("Index out of bound");
        Node<T> ptr = headNode.next;
        for (int i = 0; i < idx; i++, ptr = ptr.next);
        ptr.value = value;
    }

    @Override
    public T delete(int idx) {
        if (idx < 0 || idx >= length) throw new RuntimeException("Index of bound");
        final T target; Node<T> ptr = headNode;
        for (int i = 0; i < idx; i++) ptr = ptr.next;
        target   = ptr.next.value;
        ptr.next = ptr.next.next;
        length--;
        return target;
    }

    @Override
    public int indexOf(T value) {
        if (length == 0 || value == null) return -1;
        Node<T> ptr = headNode.next; int cursor = 0;
        while (ptr != null) {
            if (Objects.equals(ptr.value, value)) return cursor;
            cursor++; ptr = ptr.next;
        }
        return -1;
    }

    private static class Node<T> {
        Node<T> next;  //  指向下一个结点
        T       value; // 值域
        public Node(Node<T> next, T value) {
            this.next = next;
            this.value = value;
        }
    }
}
