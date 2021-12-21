package net.tdiant.tinyjvm.runtime;

public class InstanceArrayInstance extends BaseArrayInstance {

    private final Instance[] arr;

    public InstanceArrayInstance(Clazz clazz, Instance[] arr) {
        super(clazz, arr.length);
        this.arr = arr;
    }

    public Instance[] getArray() {
        return arr;
    }

    public Instance get(int idx) {
        return arr[idx];
    }

    public int length() {
        return arr.length;
    }
}
