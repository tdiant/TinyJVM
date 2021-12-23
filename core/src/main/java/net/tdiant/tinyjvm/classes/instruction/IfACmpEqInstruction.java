package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class IfACmpEqInstruction extends Instruction {

    private final int idx;

    public IfACmpEqInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        Object a = frame.getOperandStack().popRef();
        Object b = frame.getOperandStack().popRef();
        if (a == b) {
            frame.setNextPc(frame.getPc() + idx);
        }
    }

    @Override
    public String toString() {
        return "if_acmpeq " + idx;
    }
}
