package algo.list;

import ds.list.impl.SequentialList;

import java.util.function.Function;

/**
 * 将一个顺序表循环左移p位
 *  see also => LeetCode； 2010-408统考（数据结构）； 王道/顺序表/综合分析题
 *  将顺序表按照循环移位的次数分为AB两个部分 A(0, p-1)，B(p, length-1)
 *  A' = Reverse(A)
 *  B' = Reverse(B)
 *  Reverse(A'B')
 */
public class CircuitLeftShift implements Function<SequentialList<Integer>, SequentialList<Integer>> {

    private final int p;

    public CircuitLeftShift(int num) { p = num; }

    @Override
    public SequentialList<Integer> apply(SequentialList<Integer> list) {
        // reverse A(0..p-1)
        for (int i = 0, j = p-1; i < j; i++, j--) {
            Integer temp = list.at(i);
            list.set(i, list.at(j));
            list.set(j, temp);
        }
        // reverse B(p..length-1)
        for (int i = p, j = list.length()-1; i < j; i++, j--) {
            Integer temp = list.at(i);
            list.set(i, list.at(j));
            list.set(j, temp);
        }
        list.reverse();
        return list;
    }
}
