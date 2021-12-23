package net.tdiant.tinyjvm.runtime;

import java.util.HashMap;
import java.util.Map;

public class InstanceArrayInstance extends BaseArrayInstance {

    private final Map<Integer, Object> arr = new HashMap<>();

    public InstanceArrayInstance(Clazz clazz, Object[] arr) {
        super(clazz, arr.length * 2 + 10);
        for (int i = 0; i < arr.length; i++) {
            this.arr.put(i, arr[i]);
        }
    }

    public Object get(int idx) {
        return arr.getOrDefault(idx, null);
    }

    public void set(int idx, Object obj) {
        arr.put(idx, obj);
    }

    public int length() {
        return arr.size();
    }

    @Override
    public String toString() {
        return "InstanceArrayInstance{" +
                "arr=" + arr +
                '}';
    }
}
