package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class FNegInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        float a = frame.getOperandStack().pop().getFloat();
        frame.getOperandStack().push(new Slot(a * (-1)));
    }

    @Override
    public String toString() {
        return "fneg";
    }
}
