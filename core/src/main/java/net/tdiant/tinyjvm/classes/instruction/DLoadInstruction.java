package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class DLoadInstruction extends Instruction {

    private final int idx;

    public DLoadInstruction(int idx) {
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
        frame.getOperandStack().pushDouble(
                frame.getLocalVars().getDouble(idx)
        );
    }

    @Override
    public String toString() {
        return "dload " + idx;
    }
}
