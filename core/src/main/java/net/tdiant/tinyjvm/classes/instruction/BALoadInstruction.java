package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.PrimitiveArray;
import net.tdiant.tinyjvm.runtime.Slot;

public class BALoadInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        int idx = frame.getOperandStack().popInt();
        PrimitiveArray arr = (PrimitiveArray) frame.getOperandStack().popRef();
        frame.getOperandStack().push(new Slot(arr.ints[idx]));
    }

    @Override
    public String toString() {
        return "baload";
    }
}
