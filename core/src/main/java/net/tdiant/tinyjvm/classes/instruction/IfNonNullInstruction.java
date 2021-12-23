package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class IfNonNullInstruction extends Instruction {

    public final int idx;

    public IfNonNullInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        if (frame.getOperandStack().popRef() != null) {
            frame.setNextPc(frame.getPc() + idx);
        }
    }

    @Override
    public String toString() {
        return "ifnonnull " + idx;
    }

}
