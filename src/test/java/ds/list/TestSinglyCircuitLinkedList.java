package ds.list;

import ds.list.impl.SinglyCircuitLinkedList;

public class TestSinglyCircuitLinkedList extends TestIntListBase {
    {
        listSupplier = SinglyCircuitLinkedList<Integer>::new;
    }
}
