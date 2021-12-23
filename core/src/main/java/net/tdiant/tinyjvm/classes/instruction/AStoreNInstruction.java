package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class AStoreNInstruction extends Instruction {

    private final int idx;

    public AStoreNInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public void run(Frame frame) {
        frame.getLocalVars().set(idx, new Slot(
                frame.getOperandStack().popRef()
        ));
    }

    @Override
    public String toString() {
        return "astore_" + idx;
    }
}
