package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class DNegInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        double a = frame.getOperandStack().popDouble();
        frame.getOperandStack().pushDouble(-1 * a);
    }

    @Override
    public String toString() {
        return "dneg";
    }
}
