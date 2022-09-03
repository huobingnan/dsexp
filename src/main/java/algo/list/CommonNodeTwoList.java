package algo.list;

import ds.list.impl.SinglyLinkedList;

import java.util.function.BiFunction;

/**
 * 问题描述：
 *      给定两个链表，两个链表中有重合的部分，例如：
 *      1 --> 2 --> 3  \
 *                       4 --> 5 --> 6
 *      2 --> 3 --> 7  /
 *      给出一个高效的算法，找出两个链表第一个公共的节点
 */
public class CommonNodeTwoList implements
        BiFunction<SinglyLinkedList<Character>, SinglyLinkedList<Character>, Character> {

    // 时间复杂度： O(N)/O(a + b) a,b为两个链表长度； 空间复杂度：O(1)
    @Override
    public Character apply(SinglyLinkedList<Character> str1,
                           SinglyLinkedList<Character> str2) {
        var ptr1 = str1.firstNode();
        var ptr2 = str2.firstNode();
        for (;;) {
            if (ptr1 == ptr2) break;
            if (ptr1 == null) ptr1 = str2.firstNode();
            else ptr1 = ptr1.next;
            if (ptr2 == null) ptr2 = str1.firstNode();
            else ptr2 = ptr2.next;
        }
        return ptr1.value;
    }
}
