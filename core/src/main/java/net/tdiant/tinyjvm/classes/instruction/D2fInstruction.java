package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class D2fInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        double x = frame.getOperandStack().popDouble();
        frame.getOperandStack().pushFloat((float) x);
    }

    @Override
    public String toString() {
        return "d2f";
    }
}
