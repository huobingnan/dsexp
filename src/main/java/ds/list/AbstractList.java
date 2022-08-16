package ds.list;

public abstract class AbstractList<T> implements IList<T> {
    @Override
    public void set(int idx, T value) { throw new UnsupportedOperationException("'set' is not supported"); }

    @Override
    public boolean isEmpty() { throw new UnsupportedOperationException("'isEmpty' is not supported"); }

    @Override
    public int length() { throw new UnsupportedOperationException("'length' is not supported"); }

    @Override
    public int indexOf(T value) { throw new UnsupportedOperationException("'indexOf' is not supported"); }

    @Override
    public void insert(int idx, T value) { throw new UnsupportedOperationException("'insert' is not supported"); }

    @Override
    public void pushBack(T value) { throw new UnsupportedOperationException("'pushBack' is not supported"); }

    @Override
    public T at(int idx) { throw new UnsupportedOperationException("'at' is not supported"); }

    @Override
    public T delete(int idx) { throw new UnsupportedOperationException("'delete is not supported"); }

    @Override
    public void include(IList<T> another) { throw new UnsupportedOperationException("'include' is not supported"); }

    @Override
    public void reverse() { throw new UnsupportedOperationException("'reverse' is not supported"); }
}
