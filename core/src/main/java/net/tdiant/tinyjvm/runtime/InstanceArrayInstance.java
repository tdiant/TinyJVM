package net.tdiant.tinyjvm.runtime;

public class InstanceArrayInstance extends BaseArrayInstance {

    private final Object[] arr;

    public InstanceArrayInstance(Clazz clazz, Object[] arr) {
        super(clazz, arr.length);
        this.arr = arr;
    }

    public Object[] getArray() {
        return arr;
    }

    public Object get(int idx) {
        return arr[idx];
    }

    public void set(int idx, Object obj) {
        arr[idx] = obj;
    }

    public int length() {
        return arr.length;
    }
}
