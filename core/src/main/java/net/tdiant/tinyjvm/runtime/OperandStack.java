package net.tdiant.tinyjvm.runtime;

/**
 * 操作数调用栈
 */
public class OperandStack {

    private final Slot[] arr;
    private int cnt = 0;

    public OperandStack(int size) {
        this.arr = new Slot[size * 2];
    }

    public void push(Slot slot) {
        arr[cnt++] = slot;
    }

    public Slot pop() {
        Slot s = arr[--cnt];
        arr[cnt] = null;
        return s;
    }

}
