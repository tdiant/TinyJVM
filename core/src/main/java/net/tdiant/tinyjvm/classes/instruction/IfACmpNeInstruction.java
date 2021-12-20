package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class IfACmpNeInstruction extends Instruction {

    private final int idx;

    public IfACmpNeInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        Object a = frame.getOperandStack().pop().getInstance();
        Object b = frame.getOperandStack().pop().getInstance();
        if (a != b) {
            frame.setNextPc(frame.getPc() + idx);
        }
    }

    @Override
    public String toString() {
        return "if_acmpne " + idx;
    }
}
