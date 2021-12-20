package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class BiPushInstruction extends Instruction {

    public final byte num;

    public BiPushInstruction(byte num) {
        this.num = num;
    }

    public byte getNum() {
        return num;
    }

    @Override
    public void run(Frame frame) {
        frame.getOperandStack().push(new Slot(num));
    }

    @Override
    public int delta() {
        return 2;
    }

    @Override
    public String toString() {
        return "bipush " + num;
    }
}
