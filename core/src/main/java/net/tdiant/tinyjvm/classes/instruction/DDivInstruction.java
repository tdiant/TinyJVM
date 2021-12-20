package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class DDivInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        double a = frame.getOperandStack().pop().getDouble();
        double b = frame.getOperandStack().pop().getDouble();
        if (a == 0) throw new NumberFormatException();
        frame.getOperandStack().push(new Slot(b / a));
    }

    @Override
    public String toString() {
        return "ddiv";
    }
}
