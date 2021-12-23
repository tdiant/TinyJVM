package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

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
        frame.getOperandStack().pushInt(num);
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
