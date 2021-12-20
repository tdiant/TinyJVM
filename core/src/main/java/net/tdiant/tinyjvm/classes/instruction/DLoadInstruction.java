package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class DLoadInstruction extends Instruction {

    private final int idx;

    public DLoadInstruction(int idx) {
        this.idx = idx;
    }

    public int getIndex() {
        return idx;
    }

    @Override
    public int delta() {
        return 2;
    }

    @Override
    public void run(Frame frame) {
        frame.getOperandStack().push(new Slot(
                frame.getLocalVars().get(idx).getDouble()
        ));
    }

    @Override
    public String toString() {
        return "dload " + idx;
    }
}
