package etc;

import java.util.ArrayList;
import java.util.List;

public class CollectionTest {
    private final List<String> ROList = new ArrayList<>();
    private final List<String> RWList = new ArrayList<>();

    public void setRWList(List<String> list) {
        RWList.clear();
        RWList.addAll(list);
    }

    public List<String> getROList() {
        return ROList;
    }

    public List<String> getRWList() {
        return RWList;
    }
}
