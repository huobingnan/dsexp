package algo.list;

import ds.list.impl.SinglyLinkedList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonNodeTwoListTest {

    final CommonNodeTwoList solution = new CommonNodeTwoList();

    @Test
    public void shouldReturnIAsResult() {
        var str1 = new SinglyLinkedList<Character>();
        str1.pushBack('l'); str1.pushBack('o'); str1.pushBack('a'); str1.pushBack('d');
        var str2 = new SinglyLinkedList<Character>();
        str2.pushBack('b'); str2.pushBack('e');
        var common = new SinglyLinkedList<Character>();
        common.pushBack('i'); common.pushBack('n'); common.pushBack('g');
        // connect
        var tail = str1.firstNode();
        for (int i = 0; i < str1.length()-1; i++) tail = tail.next;
        tail.next = common.firstNode();
        tail = str2.firstNode();
        for (int i = 0; i < str2.length()-1; i++) tail = tail.next;
        tail.next = common.firstNode();
        assertEquals('i', solution.apply(str1, str2));
    }
}
