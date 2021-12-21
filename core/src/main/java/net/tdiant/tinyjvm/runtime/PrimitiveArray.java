package net.tdiant.tinyjvm.runtime;

import net.tdiant.tinyjvm.TinyJVM;

public class PrimitiveArray extends BaseArrayInstance {

    public int[] ints;
    public long[] longs;
    public float[] floats;
    public double[] doubles;

    private PrimitiveArray(Clazz clazz, int size) {
        super(clazz, size);
    }

    public static PrimitiveArray charArray(int size) {
        Clazz arrCls = TinyJVM.vm.getHeap().getClazz("[C");
        PrimitiveArray array = new PrimitiveArray(arrCls, size);
        array.ints = new int[size];
        return array;
    }

    public static PrimitiveArray boolArray(int size) {
        Clazz arrCls = TinyJVM.vm.getHeap().getClazz("[Z");
        PrimitiveArray array = new PrimitiveArray(arrCls, size);
        array.ints = new int[size];
        return array;
    }

    public static PrimitiveArray byteArray(int size) {
        Clazz arrCls = TinyJVM.vm.getHeap().getClazz("[B");
        PrimitiveArray array = new PrimitiveArray(arrCls, size);
        array.ints = new int[size];
        return array;
    }

    public static PrimitiveArray shortArray(int size) {
        Clazz arrCls = TinyJVM.vm.getHeap().getClazz("[S");
        PrimitiveArray array = new PrimitiveArray(arrCls, size);
        array.ints = new int[size];
        return array;
    }

    public static PrimitiveArray intArray(int size) {
        Clazz arrCls = TinyJVM.vm.getHeap().getClazz("[I");
        PrimitiveArray array = new PrimitiveArray(arrCls, size);
        array.ints = new int[size];
        return array;
    }

    public static PrimitiveArray longArray(int size) {
        Clazz arrCls = TinyJVM.vm.getHeap().getClazz("[J");
        PrimitiveArray array = new PrimitiveArray(arrCls, size);
        array.longs = new long[size];
        return array;
    }

    public static PrimitiveArray floatArray(int size) {
        Clazz arrCls = TinyJVM.vm.getHeap().getClazz("[F");
        PrimitiveArray array = new PrimitiveArray(arrCls, size);
        array.floats = new float[size];
        return array;
    }

    public static PrimitiveArray doubleArray(int size) {
        Clazz arrCls = TinyJVM.vm.getHeap().getClazz("[D");
        PrimitiveArray array = new PrimitiveArray(arrCls, size);
        array.doubles = new double[size];
        return array;
    }
}
