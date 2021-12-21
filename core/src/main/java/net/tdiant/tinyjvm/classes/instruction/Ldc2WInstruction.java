package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class Ldc2WInstruction extends Instruction {

    private final boolean tag; //false: long; true: double
    private final long a;
    private final double b;

    public Ldc2WInstruction(long x) {
        this.a = x;
        this.b = 0;
        tag = false;
    }

    public Ldc2WInstruction(double x) {
        this.b = x;
        this.a = 0;
        tag = true;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        if (tag) {
            frame.getOperandStack().push(new Slot(b));
        } else {
            frame.getOperandStack().push(new Slot(a));
        }
    }

    @Override
    public String toString() {
        return "ldc2w " + (tag ? "" + b : "" + a);
    }

    public long getLong() {
        return a;
    }

    public double getDouble() {
        return b;
    }
}
