package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class F2lInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        float x = frame.getOperandStack().popFloat();
        frame.getOperandStack().pushLong((long) x);
    }

    @Override
    public String toString() {
        return "f2l";
    }
}
