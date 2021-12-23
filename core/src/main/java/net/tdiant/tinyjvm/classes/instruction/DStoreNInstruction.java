package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class DStoreNInstruction extends Instruction {

    private final int idx;

    public DStoreNInstruction(int idx) {
        this.idx = idx;
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

