package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class IStoreInstruction extends Instruction {

    private final int idx;

    public IStoreInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public int delta() {
        return 2;
    }

    @Override
    public void run(Frame frame) {
        frame.getLocalVars().setInt(idx,
                frame.getOperandStack().popInt()
        );
    }

    @Override
    public String toString() {
        return "istore " + idx;
    }
}
