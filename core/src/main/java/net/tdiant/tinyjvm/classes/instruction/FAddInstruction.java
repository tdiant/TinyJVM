package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class FAddInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        float a = frame.getOperandStack().popFloat();
        float b = frame.getOperandStack().popFloat();
        frame.getOperandStack().pushFloat(a + b);
    }

    @Override
    public String toString() {
        return "fadd";
    }
}
