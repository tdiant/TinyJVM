package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class LLoadInstruction extends Instruction {

    private final int idx;

    public LLoadInstruction(int idx) {
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
        frame.getOperandStack().pushLong(
                frame.getLocalVars().getLong(idx)
        );
    }

    @Override
    public String toString() {
        return "lload " + idx;
    }
}
