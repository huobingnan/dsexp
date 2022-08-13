package ds.list;

import ds.list.impl.SequentialList;

public class TestSequentialList extends TestIntListBase {

    {
        listSupplier = () -> {
            return new SequentialList<>(NUMBER_OF_TEST_ELEMENT);
        };
    }

}
