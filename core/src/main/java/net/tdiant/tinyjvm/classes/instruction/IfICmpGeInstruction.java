package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class IfICmpGeInstruction extends Instruction {

    private final int idx;

    public IfICmpGeInstruction(int idx) {
        this.idx = idx;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        int a = frame.getOperandStack().popInt();
        int b = frame.getOperandStack().popInt();
        if (b >= a) {
            frame.setNextPc(frame.getPc() + idx);
        }
    }

    @Override
    public String toString() {
        return "if_icmpge " + idx;
    }
}
