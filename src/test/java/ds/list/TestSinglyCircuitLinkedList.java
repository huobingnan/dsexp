package ds.list;

import ds.list.impl.SinglyCircuitLinkedList;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Test singly circuit linked list")
public class TestSinglyCircuitLinkedList extends TestIntListBase {
    {
        listSupplier = SinglyCircuitLinkedList<Integer>::new;
    }
}
