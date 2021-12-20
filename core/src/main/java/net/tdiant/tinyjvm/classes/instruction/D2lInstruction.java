package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class D2lInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        double x = frame.getOperandStack().pop().getDouble();
        frame.getOperandStack().push(new Slot((long) x));
    }

    @Override
    public String toString() {
        return "d2l";
    }
}
