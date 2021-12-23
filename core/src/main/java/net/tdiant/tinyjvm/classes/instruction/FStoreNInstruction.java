package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class FStoreNInstruction extends Instruction {

    private final int idx;

    public FStoreNInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public void run(Frame frame) {
        frame.getLocalVars().setFloat(idx,
                frame.getOperandStack().popFloat()
        );
    }

    @Override
    public String toString() {
        return "fstore_" + idx;
    }
}
