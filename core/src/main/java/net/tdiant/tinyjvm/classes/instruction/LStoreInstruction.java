package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class LStoreInstruction extends Instruction {

    private final int idx;

    public LStoreInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public int delta() {
        return 2;
    }

    @Override
    public void run(Frame frame) {
        frame.getLocalVars().setLong(idx,
                frame.getOperandStack().popLong()
        );
    }

    @Override
    public String toString() {
        return "lstore " + idx;
    }
}
