package ds.list;

import ds.list.impl.DulCircuitLinkedList;

public class TestDulCircuitLinkedList extends TestIntListBase {
    {
        listSupplier = DulCircuitLinkedList<Integer>::new;
    }
}
