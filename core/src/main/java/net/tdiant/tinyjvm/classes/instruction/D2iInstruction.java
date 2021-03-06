package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class D2iInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        double x = frame.getOperandStack().popDouble();
        frame.getOperandStack().push(new Slot((int) x));
    }

    @Override
    public String toString() {
        return "d2i";
    }
}
