package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class FNegInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        float a = frame.getOperandStack().popFloat();
        frame.getOperandStack().pushFloat(a * (-1));
    }

    @Override
    public String toString() {
        return "fneg";
    }
}
