package ds.list;

import ds.list.impl.SequentialList;

public class TestSequentialList extends TestIntListBase {

    {
        listSupplier = SequentialList::new;
    }

}
