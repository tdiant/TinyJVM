package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class DLoadNInstruction extends Instruction {

    private final int idx;

    public DLoadNInstruction(int idx) {
        this.idx = idx;
    }

    public int getIndex() {
        return idx;
    }

    @Override
    public void run(Frame frame) {
        frame.getOperandStack().pushDouble(
                frame.getLocalVars().getDouble(idx)
        );
    }

    @Override
    public String toString() {
        return "dload " + idx;
    }
}