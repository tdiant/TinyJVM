package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class F2lInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        float x = frame.getOperandStack().pop().getFloat();
        frame.getOperandStack().push(new Slot((long) x));
    }

    @Override
    public String toString() {
        return "f2l";
    }
}