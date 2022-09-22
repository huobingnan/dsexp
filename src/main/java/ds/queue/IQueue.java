package ds.queue;

public interface IQueue<T> {

    public void enqueue(T element);

    public T dequeue();

    public boolean isEmpty();

    public int size();

    public T front();

}
