package ds.list.impl;

import ds.annotation.Specific;
import ds.list.AbstractList;
import ds.list.IList;

import java.util.Objects;
import java.util.RandomAccess;
import java.util.function.Predicate;

/**
 * 顺序表实现
 * @param <T> value type
 */
@SuppressWarnings("unchecked")
public class SequentialList<T> extends AbstractList<T> implements RandomAccess {
    private static final Object[] EMPTY = new Object[0];
    private int capacity; // 线性表的容量
    private Object[] elements;
    private int length; // 当前的表长

    public SequentialList() {
       capacity = 0;
       elements = EMPTY;
       length = 0;
    }

    public SequentialList(int cap) {
        // check params
        if (cap <= 0) throw new IllegalArgumentException("capacity can't be zero or negative number");
        length = 0;
        capacity = cap;
        elements = new Object[cap];
    }

    public SequentialList(Object[] init) {
        elements = init;
        length   = init.length;
        capacity = init.length;
    }

    private void checkIndexValidation(int idx) {
        if (idx >= length || idx < 0)
            throw new RuntimeException("index out of bound");
    }

    // 用于保障顺序表的容量，当容量不足时会触发扩容操作
    private void ensureCapacity() {
       if (capacity == 0) {
           // 空表
           capacity = 10; elements = new Object[capacity];
       } else {
           // 检查length与capacity
           if (length >= capacity) {
               capacity = capacity << 1; // 扩容策略为二倍扩容
               final Object[] target = new Object[capacity];
               System.arraycopy(elements, 0, target, 0, length);
               elements = target;
           }
       }
    }

    @Override
    public boolean isEmpty() { return length == 0; }

    @Override
    public int length() { return length; }

    @Override
    public int indexOf(T value) {
        if (isEmpty() || value == null) return -1;
        int res = -1;
        for (int i = 0; i < length; i++)
            if (Objects.equals(elements[i], value)) return i;
        return res;
    }

    @Override
    public void insert(int idx, T value) {
        if (idx < 0 || idx > length) throw new RuntimeException("Index of bound");
        ensureCapacity();
        for (int i = length; i > idx; i--)
            elements[i] = elements[i-1];
        elements[idx] = value; length++;
    }

    @Override
    public void pushBack(T value) { insert(length, value); }

    @Override
    public T at(int idx) {
        checkIndexValidation(idx);
        return (T) elements[idx];
    }

    @Override
    public void set(int idx, T value) {
        checkIndexValidation(idx);
        elements[idx] = value;
    }

    @Override
    public T delete(int idx) {
        checkIndexValidation(idx);
        final T res = (T) elements[idx];
        for (int i = idx; i < length-1; i++)
            elements[i] = elements[i+1];
        length--;
        return res;
    }

    @Override
    public void include(IList<T> another) {
        for (int i = 0; i < another.length(); i++) { pushBack(another.at(i)); }
    }

    @Override
    public void reverse() {
        // 采用双指针法： 实现顺序表反转
        for (int i = 0, j = length-1; i < j; i++, j--) {
            Object temp = elements[i];
            elements[i] = elements[j];
            elements[j] = temp;
        }
    }

    /**
     * 删除顺序表中满足断言条件的元素
     *  see also => 王道/顺序表章节/综合应用题/
     * @param predicate 断言条件
     */
    @Specific
    public void removeIf(Predicate<T> predicate) {
        int k = 0;
        for (int i = 0; i < length; i++) {
            if (predicate.test((T)elements[i])) k++;
            else elements[i-k] = elements[i];
        }
        // 让GC可以回收这部分内存占用
        for (int i = length-k; i < length; i++) elements[i] = null;
        length = length-k;
    }
}
