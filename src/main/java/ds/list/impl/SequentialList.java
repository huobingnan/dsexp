package ds.list.impl;

import ds.list.IList;

/**
 * 顺序表实现
 * @param <T> value type
 */
@SuppressWarnings("unchecked")
public class SequentialList<T> implements IList<T> {
    private final int capacity; // 线性表的容量
    private final Object[] elements;
    private int length; // 当前的表长

    public SequentialList(int cap) {
        // check params
        if (cap <= 0) throw new IllegalArgumentException("capacity can't be zero or negative number");
        length = 0;
        capacity = cap;
        elements = new Object[cap];
    }

    private void checkIndexValidation(int idx) {
        if (idx >= length || idx < 0)
            throw new IllegalArgumentException("index out of bound");
    }

    @Override
    public boolean isEmpty() { return length == 0; }

    @Override
    public int length() { return length; }

    @Override
    public int indexOf(T value) {
        int res = -1;
        for (int i = 0; i < length; i++)
            if (elements[i].equals(value)) return i;
        return res;
    }

    @Override
    public void insert(int idx, T value) {
        if (length >= capacity)
            throw new RuntimeException("list is full");
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
    public T delete(int idx) {
        checkIndexValidation(idx);
        final T res = (T) elements[idx];
        for (int i = idx; i < length-1; i++)
            elements[i] = elements[i+1];
        return res;
    }

    @Override
    public void include(IList<T> another) {

    }
}
