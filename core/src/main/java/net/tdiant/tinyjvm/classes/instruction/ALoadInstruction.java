package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class ALoadInstruction extends Instruction {

    private final int idx;

    public ALoadInstruction(int idx) {
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
        frame.getOperandStack().pushRef(
                frame.getLocalVars().getRef(idx)
        );
    }

    @Override
    public String toString() {
        return "aload " + idx;
    }

}
