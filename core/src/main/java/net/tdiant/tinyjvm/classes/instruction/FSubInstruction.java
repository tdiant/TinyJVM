package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class FSubInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        float a = frame.getOperandStack().pop().getFloat();
        float b = frame.getOperandStack().pop().getFloat();
        frame.getOperandStack().push(new Slot(a - b));
    }

    @Override
    public String toString() {
        return "fsub";
    }
}
