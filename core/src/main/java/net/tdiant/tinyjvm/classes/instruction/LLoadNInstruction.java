package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class LLoadNInstruction  extends Instruction {

    private final int idx;

    public LLoadNInstruction(int idx) {
        this.idx = idx;
    }

    public int getIndex() {
        return idx;
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
