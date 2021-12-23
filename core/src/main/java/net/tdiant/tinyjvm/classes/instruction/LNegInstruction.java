package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class LNegInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        long a = frame.getOperandStack().popLong();
        frame.getOperandStack().pushLong(a * (-1));
    }

    @Override
    public String toString() {
        return "lneg";
    }
}
