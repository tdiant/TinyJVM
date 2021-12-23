package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class L2fInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        long x = frame.getOperandStack().popLong();
        frame.getOperandStack().pushFloat((float) x);
    }

    @Override
    public String toString() {
        return "l2f";
    }
}
