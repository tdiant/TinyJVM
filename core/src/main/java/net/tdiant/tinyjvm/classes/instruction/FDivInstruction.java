package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class FDivInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        float a = frame.getOperandStack().pop().getFloat();
        float b = frame.getOperandStack().pop().getFloat();
        if (a == 0) throw new NumberFormatException();
        frame.getOperandStack().push(new Slot(b / a));
    }

    @Override
    public String toString() {
        return "fdiv";
    }
}
