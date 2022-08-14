package ds.list;

import ds.list.impl.SinglyLinkedList;

public class TestSinglyLinkedList extends TestIntListBase {
    {
        listSupplier = SinglyLinkedList<Integer>::new;
    }
}
