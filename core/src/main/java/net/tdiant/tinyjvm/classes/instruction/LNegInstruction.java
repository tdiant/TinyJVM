package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class LNegInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        long a = frame.getOperandStack().pop().getLong();
        frame.getOperandStack().push(new Slot(a * (-1)));
    }

    @Override
    public String toString() {
        return "lneg";
    }
}
