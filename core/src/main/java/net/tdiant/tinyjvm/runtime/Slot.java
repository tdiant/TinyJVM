package net.tdiant.tinyjvm.runtime;

public class Slot {

    private SlotUnit high, low;

    public Slot(SlotUnit high, SlotUnit low) {
        this.high = high;
        this.low = low;
    }

    public Slot(SlotUnit high) {
        this.high = high;
        this.low = null;
    }

    public Slot(Instance inst) {
        set(inst);
    }

    public Slot(int num) {
        set(num);
    }

    public Slot(long num) {
        set(num);
    }

    public Slot(float num) {
        set(num);
    }

    public Slot(double num) {
        set(num);
    }

    public SlotUnit getHigh() {
        return high;
    }

    public SlotUnit getLow() {
        return low;
    }

    public void set(Instance inst) {
        this.high = new SlotUnit(inst);
        this.low = null;
    }

    public void set(int num) {
        this.high = new SlotUnit(num);
        this.low = null;
    }

    public void set(long num) {
        this.high = new SlotUnit((int) (num >> 32));
        this.low = new SlotUnit((int) (num & 0x000000ffffffffL));
    }

    public void set(float num) {
        set(Float.floatToIntBits(num));
    }

    public void set(double num) {
        set(Double.doubleToLongBits(num));
    }

    public Instance getInstance() {
        return high.getInstance();
    }

    public int getInt() {
        return high.getNum();
    }

    public long getLong() {
        return ((high.getNum() & 0x000000ffffffffL) << 32) | (low.getNum() & 0x00000000ffffffffL);
    }

    public float getFloat() {
        return Float.intBitsToFloat(high.getNum());
    }

    public double getDouble() {
        return Double.longBitsToDouble(getLong());
    }

    public static class SlotUnit {

        private Object obj;
        private boolean tag; //false: int; true: instance

        public SlotUnit(int num) {
            set(num);
        }

        public SlotUnit(Instance inst) {
            set(inst);
        }

        public int getNum() {
            if (tag) return 0;
            return (Integer) obj;
        }

        public Instance getInstance() {
            if (!tag) return null;
            return (Instance) obj;
        }

        public void set(int num) {
            tag = false;
            obj = num;
        }

        public void set(Instance inst) {
            tag = true;
            obj = inst;
        }
    }


}
