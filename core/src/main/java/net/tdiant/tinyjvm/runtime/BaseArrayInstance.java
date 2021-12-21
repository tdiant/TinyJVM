package net.tdiant.tinyjvm.runtime;

public class BaseArrayInstance extends Instance {

    private final int length;

    public BaseArrayInstance(Clazz clazz, int length) {
        super(clazz);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

}
