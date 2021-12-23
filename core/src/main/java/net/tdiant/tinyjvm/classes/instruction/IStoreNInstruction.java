package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class IStoreNInstruction extends Instruction {

    private final int idx;

    public IStoreNInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public void run(Frame frame) {
        frame.getLocalVars().set(idx, new Slot(
                frame.getOperandStack().popInt()
        ));
    }

    @Override
    public String toString() {
        return "istore_" + idx;
    }
}
