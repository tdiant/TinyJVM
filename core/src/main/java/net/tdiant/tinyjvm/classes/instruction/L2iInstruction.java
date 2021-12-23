package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class L2iInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        long x = frame.getOperandStack().popLong();
        frame.getOperandStack().pushInt((int) x);
    }

    @Override
    public String toString() {
        return "l2i";
    }
}
