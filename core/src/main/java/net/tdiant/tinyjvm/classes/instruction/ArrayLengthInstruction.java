package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.BaseArrayInstance;
import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class ArrayLengthInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        BaseArrayInstance arr = (BaseArrayInstance) frame.getOperandStack().pop().getInstance();
        frame.getOperandStack().push(new Slot(arr.getLength()));
    }

    @Override
    public String toString() {
        return "arraylength";
    }
}
