package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class LDivInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        long a = frame.getOperandStack().pop().getLong();
        long b = frame.getOperandStack().pop().getLong();
        if (a == 0) throw new NumberFormatException();
        frame.getOperandStack().push(new Slot(b / a));
    }

    @Override
    public String toString() {
        return "ldiv";
    }
}
