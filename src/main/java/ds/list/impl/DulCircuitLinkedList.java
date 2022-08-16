package ds.list.impl;

import ds.list.AbstractList;

import java.io.Serializable;
import java.util.Objects;

/**
 * 双向循环链表实现
 * @param <T>
 */
public class DulCircuitLinkedList<T> extends AbstractList<T> {

    private DulNode<T> head;   // 头指针
    private int        length; // 链表长度

    public DulCircuitLinkedList() { head = null; length = 0; }

    @Override
    public boolean isEmpty() { return length == 0; }

    @Override
    public int length() { return length; }

    @Override
    public void pushBack(T value) { insert(length, value); }

    @Override
    public void insert(int idx, T value) {
        if (idx < 0 || idx > length) throw new RuntimeException("Index out of bound");
        final DulNode<T> node = new DulNode<>(value, null, null);
        if (idx == 0 || idx == length) {
            // 头插即是尾插， 时间复杂度： O(1)
            if (head != null) {
                node.prior = head.prior; // 旧的头指针的前驱域指向了尾结点
                node.next  = head;       // 新的头指向旧的头
                // 旧结点建立新关系
                head.prior.next = node;  // 尾结点的后继指针要更新到新的头结点
                head.prior      = node;  // 旧的头的前驱指针要更新到新的头结点
            } else {
                node.next = node; node.prior = node;
            }
            if (idx == 0) head = node;   // 头插与尾插不同的是，头插需要更新头引用
        } else {
            DulNode<T> ptr = head;
            if (idx > length/2) for (int i = 0; i < length - idx; i++, ptr = ptr.prior);
            else for (int i = 0; i < idx; i++, ptr = ptr.next);
            node.prior = ptr.prior;
            node.next  = ptr;
            ptr.prior.next = node;
            ptr.prior      = node;
        }
        length++;
    }

    @Override
    public int indexOf(T value) {
        if (isEmpty() || value == null) return -1;
        if (Objects.equals(head.value, value)) return 0;
        DulNode<T> ptr = head.next;
        for (int i = 1; ptr != null && ptr != head; i++, ptr = ptr.next) {
            if (Objects.equals(ptr.value, value)) return i;
        }
        return -1;
    }

    @Override
    public T at(int idx) { return locate(idx).value; }

    @Override
    public void set(int idx, T value) { locate(idx).value = value; }

    @Override
    public T delete(int idx) {
        DulNode<T> ptr = locate(idx);
        final T resValue = ptr.value; length--;
        if (ptr == head) head = head.next;
        ptr.prior.next = ptr.next;
        ptr.next.prior = ptr.prior;
        return resValue;
    }

    @Override
    public void reverse() {
        if (isEmpty()) return;
        DulNode<T> prev = head.prior, ptr = head, tail = head.prior, next = null;
        for (;;) {
            next = ptr.next;
            ptr.next = prev;
            prev.prior = ptr;
            if (ptr == tail) break;
            prev = ptr; ptr = next;
        }
        head = ptr;
    }

    private DulNode<T> locate(int idx, int boundary) {
        if (idx < 0 || idx >= boundary) throw new RuntimeException("Index out of bound");
        // 定位节点
        DulNode<T> ptr = head;
        if (idx > length / 2) for (int i = 0; i < length-idx; i++, ptr = ptr.prior);
        else for (int i = 0; i < idx; i++, ptr = ptr.next);
        return ptr;
    }

    private DulNode<T> locate(int idx) { return locate(idx, length); }

    private static final class DulNode<T> implements Serializable {
        T          value; // 数据域
        DulNode<T> prior; // 前驱结点指针域
        DulNode<T> next;  // 后继结点指针域

        public DulNode(T value, DulNode<T> prior, DulNode<T> next) {
            this.value = value;
            this.prior = prior;
            this.next = next;
        }
    }
}
