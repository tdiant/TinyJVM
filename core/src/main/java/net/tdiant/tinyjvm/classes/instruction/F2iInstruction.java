package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class F2iInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        float x = frame.getOperandStack().popFloat();
        frame.getOperandStack().push(new Slot((int) x));
    }

    @Override
    public String toString() {
        return "f2i";
    }
}
