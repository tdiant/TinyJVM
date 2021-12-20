package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class SiPushInstruction extends Instruction {

    public final short num;

    public SiPushInstruction(short num) {
        this.num = num;
    }

    public short getNum() {
        return num;
    }

    @Override
    public void run(Frame frame) {
        frame.getOperandStack().push(new Slot(num));
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public String toString() {
        return "sipush " + num;
    }

}
