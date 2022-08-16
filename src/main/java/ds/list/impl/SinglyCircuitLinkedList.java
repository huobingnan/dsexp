package ds.list.impl;

import ds.list.AbstractList;

import java.nio.file.Path;
import java.util.Objects;

/**
 * 单向循环链表实现
 * @param <T> 数据类型
 */
public class SinglyCircuitLinkedList<T> extends AbstractList<T> {

    private Node<T> tail;   // 链表的尾指针
    private int     length; // 链表长度

    public SinglyCircuitLinkedList() {
        tail = null; length = 0;
    }

    @Override
    public boolean isEmpty() { return length == 0; }

    @Override
    public int length() { return length; }

    @Override
    public void insert(int idx, T value) {
        if (idx < 0 || idx > length) throw new RuntimeException("Index out of bound");
        final Node<T> node = new Node<>(value, null);
        if (idx == 0) {
            // 头插
            if (tail != null) {
                node.next = tail.next; // 指向头指针
                tail.next = node;      // 确立新的头指针
            } else {
                node.next = node; tail = node;
            }
        } else if (idx == length) {
            // 尾插
            node.next = tail.next;  // 新的尾指针指向头指针
            tail.next = node;       // 旧尾指针指向新尾指针
            tail      = node;       // 更新尾指针
        } else {
            Node<T> ptr = tail;                            // 选择tail作为起始引用
            for (int i = 0; i < idx; i++, ptr = ptr.next); // 找到插入位置的前驱节点
            node.next = ptr.next; ptr.next = node;
        }
        length++;
    }

    @Override
    public void pushBack(T value) { insert(length, value); }

    @Override
    public int indexOf(T value) {
        if (isEmpty() || value == null) return -1;
        if (Objects.equals(tail.value, value)) return length-1; // 首先比较尾引用
        Node<T> ptr = tail.next;                                // 找到头指针
        for (int i = 0; ptr != tail; i++, ptr = ptr.next)       // 循环中止的条件是：引用等于尾引用
            if (Objects.equals(ptr.value, value)) return i;
        return -1;
    }

    @Override
    public T delete(int idx) {
        if (idx < 0 || idx >= length) throw new RuntimeException("Index out of bound");
        Node<T> ptr = tail; final T res; length--;
        for (int i = 0; i < idx; i++, ptr = ptr.next); // 寻找前驱元素
        res = ptr.next.value;
        if (ptr.next == tail) tail = ptr;              // 若删除结点是尾结点：更新尾引用
        ptr.next = ptr.next.next;
        return res;
    }

    @Override
    public T at(int idx) {
        if (idx < 0 || idx >= length) throw new RuntimeException("Index out of bound");
        if (idx == length-1) return tail.value;        // 查找末尾是O(1)的开销
        Node<T> ptr = tail.next;
        for (int i = 0; i < idx; i++, ptr = ptr.next); // 查找头也是O(1)的开销
        return ptr.value;
    }

    @Override
    public void set(int idx, T value) {
        if (idx < 0 || idx >= length) throw new RuntimeException("Index out of bound");
        if (idx == length-1) tail.value = value; // 设置末尾是O(1)的开销
        Node<T> ptr = tail.next;
        for (int i = 0; i < idx; i++, ptr = ptr.next);
        ptr.value = value;
    }

    @Override
    public void reverse() {
        if (tail == null) return; // 空表直接不处理
        Node<T> ptr = tail.next, prev = tail, next = null;
        for (;;) {
            next = ptr.next;
            ptr.next = prev;
            if (ptr == tail) break;
            prev = ptr; ptr = next;
        }
        tail = next;
    }

    private static final class Node<T> {
        T       value; // 数据域
        Node<T> next;  // 指针域

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}

