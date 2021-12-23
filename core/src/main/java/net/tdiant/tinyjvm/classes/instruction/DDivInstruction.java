package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class DDivInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        double a = frame.getOperandStack().popDouble();
        double b = frame.getOperandStack().popDouble();
        if (a == 0) throw new NumberFormatException();
        frame.getOperandStack().pushDouble(b / a);
    }

    @Override
    public String toString() {
        return "ddiv";
    }
}
