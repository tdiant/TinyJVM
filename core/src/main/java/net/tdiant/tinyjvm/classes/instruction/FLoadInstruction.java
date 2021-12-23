package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class FLoadInstruction extends Instruction {

    private final int idx;

    public FLoadInstruction(int idx) {
        this.idx = idx;
    }

    public int getIndex() {
        return idx;
    }

    @Override
    public int delta() {
        return 2;
    }

    @Override
    public void run(Frame frame) {
        frame.getOperandStack().pushFloat(
                frame.getLocalVars().getFloat(idx)
        );
    }

    @Override
    public String toString() {
        return "fload " + idx;
    }
}
