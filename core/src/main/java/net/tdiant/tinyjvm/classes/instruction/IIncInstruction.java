package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class IIncInstruction extends Instruction {

    private final int idx;
    private final int num;

    public IIncInstruction(int idx, int num) {
        this.idx = idx;
        this.num = num;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        int x = frame.getLocalVars().getInt(idx);
        frame.getLocalVars().set(idx, new Slot(x + num));
    }

    @Override
    public String toString() {
        return "iinc " + idx + " " + num;
    }
}
