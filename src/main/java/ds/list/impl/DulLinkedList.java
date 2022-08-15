package ds.list.impl;

import ds.annotation.Experimental;
import ds.list.AbstractList;

import java.util.Objects;

/**
 * 双向链表实现
 * @param <T> 元素类型
 */
@Experimental
public class DulLinkedList<T> extends AbstractList<T> {

    private DulNode<T> head;
    private int        length;

    public DulLinkedList() {
        head = null; length = 0;
    }

    @Override
    public boolean isEmpty() { return length == 0; }

    @Override
    public int length() { return length; }

    @Override
    public void insert(int idx, T value) {
        if (idx < 0 || idx > length) throw new RuntimeException("Index out of bound");
        final DulNode<T> node = new DulNode<>(value, null, null);
        if (idx == 0) {
            // 头插
            if (head != null) {
                node.prior = head.prior; node.next = head;
                head.prior = node;
            }
            head = node;
        } else if (idx == length) {
            // 尾插
            DulNode<T> ptr = head;
            for (int i = 0; i < idx-1; i++) ptr = ptr.next;
            node.prior = ptr; ptr.next = node;
        } else {
            // 中间插入
            DulNode<T> ptr = head;
            for (int i = 0; i < idx; i++) ptr = ptr.next;
            node.prior = ptr.prior; node.next = ptr;
            ptr.prior.next = node; ptr.prior = node;
        }
        length++;
    }

    @Override
    public void pushBack(T value) { insert(length, value); }

    @Override
    public int indexOf(T value) {
        if (isEmpty() || value == null) return -1;
        DulNode<T> ptr = head;
        for (int i = 0; ptr != null; i++, ptr = ptr.next) {
            if (Objects.equals(ptr.value, value)) return i;
        }
        return -1;
    }

    @Override
    public T at(int idx) {
        if (idx < 0 || idx >= length) throw new RuntimeException("Index out of bound");
        DulNode<T> ptr = head;
        for (int i = 0; i < idx; i++) ptr = ptr.next;
        return ptr.value;
    }

    @Override
    public T delete(int idx) {
        if (idx < 0 || idx >= length) throw new RuntimeException("Index out of bound");
        final T res;;
        if (idx == 0) {
            // 头删
            res = head.value;
            head = head.next;
            if (head != null) head.prior = null;
        } else {
            DulNode<T> ptr = head;
            for (int i = 0; i < idx; i++) ptr = ptr.next;
            res = ptr.value;
            ptr.prior.next = ptr.next;
            if (ptr.next != null) ptr.next.prior = ptr.prior;
        }
        length--;
        return res;
    }

    private static class DulNode<T> {
        T          value; // 数据域
        DulNode<T> next;  // 后继节点域
        DulNode<T> prior; // 前驱节点域

        public DulNode(T value, DulNode<T> next, DulNode<T> prior) {
            this.value = value;
            this.next = next;
            this.prior = prior;
        }
    }
}
