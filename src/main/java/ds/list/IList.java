package ds.list;

/**
 * 线性表接口定义
 * @param <T> 元素的类型
 */
public interface IList<T> {

    /**
     * 线性表是否为空
     * @return
     */
    public boolean isEmpty();

    /**
     * 查询线性表的长度
     * @return 线性表长度
     */
    public int length();

    /**
     * 定位指定元素在线性表中的索引
     * @param value 元素
     * @return 索引
     */
    public int indexOf(T value);

    /**
     * 在线性表的指定位置插入一个元素，若插入位置不合法则会抛出RuntimeException.
     *  insert -> insertBefore，在给定索引位置前插入一个元素
     * @param idx 插入点
     * @param value 元素
     */
    public void insert(int idx, T value);

    /**
     * 在线性表的末尾插入一个元素
     * @param value 元素
     */
    public void pushBack(T value);

    /**
     * 返回线性表指定索引位置的元素，若索引值不合法则会抛出RuntimeException
     * @param idx 索引值
     * @return 元素
     */
    public T at(int idx);

    /**
     * 在指定索引处设置新的值
     * @param idx
     * @param value
     */
    public void set(int idx, T value);

    /**
     * 删除线性表中指定索引位置的元素，并返回该元素，若元素值不合法则抛出RuntimeException
     * @param idx 索引
     * @return 被删除的元素
     */
    public T delete(int idx);

    /**
     * 包含另一个线性表到本线性表中
     * @param another
     */
    public void include(IList<T> another);
}
