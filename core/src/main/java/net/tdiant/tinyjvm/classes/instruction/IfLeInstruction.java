package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class IfLeInstruction extends Instruction {

    private final int idx;

    public IfLeInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        int x = frame.getOperandStack().pop().getInt();
        if (x <= 0) {
            frame.setNextPc(frame.getPc() + idx);
        }
    }

    @Override
    public String toString() {
        return "ifle " + idx;
    }
}