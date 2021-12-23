package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class DStoreInstruction extends Instruction {

    private final int idx;

    public DStoreInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public int delta() {
        return 2;
    }

    @Override
    public void run(Frame frame) {
        frame.getLocalVars().setDouble(idx,
                frame.getOperandStack().popDouble()
        );
    }

    @Override
    public String toString() {
        return "dstore " + idx;
    }
}
