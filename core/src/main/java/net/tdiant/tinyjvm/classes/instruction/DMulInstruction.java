package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class DMulInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        double a = frame.getOperandStack().popDouble();
        double b = frame.getOperandStack().popDouble();
        frame.getOperandStack().pushDouble(a * b);
    }

    @Override
    public String toString() {
        return "dmul";
    }
}
