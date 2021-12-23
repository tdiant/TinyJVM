package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class FLoadNInstruction extends Instruction {

    private final int idx;

    public FLoadNInstruction(int idx) {
        this.idx = idx;
    }

    public int getIndex() {
        return idx;
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
