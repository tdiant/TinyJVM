package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class IfICmpLeInstruction extends Instruction {

    private final int idx;

    public IfICmpLeInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        int a = frame.getOperandStack().pop().getInt();
        int b = frame.getOperandStack().pop().getInt();
        if (b <= a) {
            frame.setNextPc(frame.getPc() + idx);
        }
    }

    @Override
    public String toString() {
        return "if_icmple " + idx;
    }
}
