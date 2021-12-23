package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class LDivInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        long a = frame.getOperandStack().popLong();
        long b = frame.getOperandStack().popLong();
        if (a == 0) throw new NumberFormatException();
        frame.getOperandStack().pushLong(b / a);
    }

    @Override
    public String toString() {
        return "ldiv";
    }
}
