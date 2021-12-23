package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class FMulInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        float a = frame.getOperandStack().popInt();
        float b = frame.getOperandStack().popInt();
        frame.getOperandStack().pushFloat(a * b);
    }

    @Override
    public String toString() {
        return "fmul";
    }
}
