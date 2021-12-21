package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class GotoWInstruction extends Instruction {

    private int idx;

    public GotoWInstruction(int idx) {
        this.idx = idx;
    }

    public int getIndex() {
        return idx;
    }

    @Override
    public int delta() {
        return 5;
    }

    @Override
    public void run(Frame frame) {
        frame.setNextPc(frame.getPc() + idx);
    }

    @Override
    public String toString() {
        return "goto_w " + idx;
    }
}
