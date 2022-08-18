package ds.stack;

/**
 * 栈接口定义
 * @param <T>
 */
public interface IStack<T> {
    /**
     * 获取栈的长度信息
     * @return 栈的长度
     */
    public int length();

    /**
     * 栈是否为空栈
     * @return
     */
    public boolean isEmpty();

    /**
     * 压栈
     * @param value
     */
    public void push(T value);

    /**
     * 出栈
     * @return
     */
    public T pop();

    /**
     * 获取栈顶元素值，不出栈
     * @return
     */
    public T top();
}
