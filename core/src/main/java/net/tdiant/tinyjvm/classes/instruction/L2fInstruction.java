package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class L2fInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        long x = frame.getOperandStack().pop().getLong();
        frame.getOperandStack().push(new Slot((float) x));
    }

    @Override
    public String toString() {
        return "l2f";
    }
}
