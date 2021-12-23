package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class ALoadNInstruction extends Instruction {

    private final int idx;

    public ALoadNInstruction(int idx) {
        this.idx = idx;
    }

    public int getIndex() {
        return idx;
    }

    @Override
    public void run(Frame frame) {
        frame.getOperandStack().pushRef(
                frame.getLocalVars().getRef(idx)
        );
    }

    @Override
    public String toString() {
        return "aload_" + idx;
    }

}
