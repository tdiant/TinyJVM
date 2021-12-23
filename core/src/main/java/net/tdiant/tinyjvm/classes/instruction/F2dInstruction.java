package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class F2dInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        float x = frame.getOperandStack().popFloat();
        frame.getOperandStack().pushDouble(x);
    }

    @Override
    public String toString() {
        return "f2d";
    }
}
