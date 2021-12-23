package net.tdiant.tinyjvm.runtime;

import net.tdiant.tinyjvm.TinyJVM;

import java.util.Arrays;

/**
 * 操作数调用栈
 */
public class OperandStack {

    private final Slot[] slots;
    private int cnt = 0;

    public OperandStack(int size) {
        this.slots = new Slot[size * 4 + 10];
        cnt = 0;
    }

    public void push(Slot slot) {
        slots[cnt++] = slot;
    }

    public Slot pop() {
        Slot s = slots[--cnt];
        slots[cnt] = null;
        return s;
    }

    public Slot get(int idx) {
        return slots[idx];
    }

    public void set(int idx, Slot slot) {
        slots[idx] = slot;
    }

    public void pushInt(int val) {
        this.slots[cnt++] = new Slot(val);
    }

    public int popInt() {
        return this.pop().num;
    }

    public void pushLong(long val) {
        int low = (int) (val & 0x000000ffffffffL); //低32位
        int high = (int) (val >> 32); //高32位
        this.push(new Slot(high));
        this.push(new Slot(low));
    }

    public long popLong() {
        int low = this.pop().num;
        int high = this.pop().num;

        long l1 = (high & 0x000000ffffffffL) << 32;
        long l2 = low & 0x00000000ffffffffL;
        return l1 | l2;
    }

    public void pushFloat(float val) {
        int tmp = Float.floatToIntBits(val);
        this.push(new Slot(tmp));
    }

    public float popFloat() {
        int tmp = this.pop().num;
        return Float.intBitsToFloat(tmp);
    }

    public void pushDouble(double val) {
        long tmp = Double.doubleToLongBits(val);

        int low = (int) (tmp & 0x000000ffffffffL); //低32位
        int high = (int) (tmp >> 32); //高32位
        this.push(new Slot(high));
        this.push(new Slot(low));
    }

    public double popDouble() {
        long tmp = this.popLong();
        return Double.longBitsToDouble(tmp);
    }

    public void pushRef(Instance val) {
        this.push(new Slot(val));
    }

    public Instance popRef() {
        return this.pop().ref;
    }

    public Slot popSlot() {
        return this.pop();
    }

    public void pushSlot(Slot val) {
        this.push(val);
    }

    public Slot[] getSlots() {
        return this.slots;
    }

    public int getCount() {
        return cnt;
    }

}
