package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class D2lInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        double x = frame.getOperandStack().popDouble();
        frame.getOperandStack().pushLong((long) x);
    }

    @Override
    public String toString() {
        return "d2l";
    }
}
