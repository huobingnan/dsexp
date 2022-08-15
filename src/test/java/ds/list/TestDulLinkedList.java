package ds.list;

import ds.list.impl.DulLinkedList;

public class TestDulLinkedList extends TestIntListBase {
    {
        listSupplier = DulLinkedList<Integer>::new;
    }
}
