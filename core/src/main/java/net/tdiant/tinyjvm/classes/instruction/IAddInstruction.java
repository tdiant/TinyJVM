package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class IAddInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        int a = frame.getOperandStack().pop().getInt();
        int b = frame.getOperandStack().pop().getInt();
        frame.getOperandStack().push(new Slot(a + b));
    }

    @Override
    public String toString() {
        return "iadd";
    }
}
