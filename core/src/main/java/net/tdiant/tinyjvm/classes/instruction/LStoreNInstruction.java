package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class LStoreNInstruction extends Instruction {

    private final int idx;

    public LStoreNInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public void run(Frame frame) {
        frame.getLocalVars().setLong(idx,
                frame.getOperandStack().popLong()
        );
    }

    @Override
    public String toString() {
        return "lstore_" + idx;
    }
}

