package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class FStoreInstruction extends Instruction {

    private final int idx;

    public FStoreInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public int delta() {
        return 2;
    }

    @Override
    public void run(Frame frame) {
        frame.getLocalVars().setFloat(idx,
                frame.getOperandStack().popFloat()
        );
    }

    @Override
    public String toString() {
        return "fstore " + idx;
    }
}
