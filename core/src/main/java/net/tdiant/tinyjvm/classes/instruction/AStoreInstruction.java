package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class AStoreInstruction extends Instruction {

    private final int idx;

    public AStoreInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public int delta() {
        return 2;
    }

    @Override
    public void run(Frame frame) {
        frame.getLocalVars().set(idx, new Slot(
                frame.getOperandStack().popRef()
        ));
    }

    @Override
    public String toString() {
        return "astore " + idx;
    }
}
