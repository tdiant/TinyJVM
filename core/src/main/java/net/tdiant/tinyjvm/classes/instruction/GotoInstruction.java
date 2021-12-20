package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class GotoInstruction extends Instruction {

    private final int idx;

    public GotoInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        frame.setNextPc(frame.getPc() + idx);
    }

    @Override
    public String toString() {
        return "goto " + idx;
    }
}
