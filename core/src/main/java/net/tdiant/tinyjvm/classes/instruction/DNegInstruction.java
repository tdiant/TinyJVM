package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class DNegInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        double a = frame.getOperandStack().pop().getDouble();
        frame.getOperandStack().push(new Slot(a * (-1)));
    }

    @Override
    public String toString() {
        return "dneg";
    }
}
