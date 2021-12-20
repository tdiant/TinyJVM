package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

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
        frame.getLocalVars().set(idx, new Slot(
                frame.getOperandStack().pop().getInt()
        ));
    }

    @Override
    public String toString() {
        return "istore " + idx;
    }
}
