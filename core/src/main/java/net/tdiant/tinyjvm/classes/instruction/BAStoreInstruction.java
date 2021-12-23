package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.PrimitiveArray;

public class BAStoreInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        int num = frame.getOperandStack().popInt();
        int idx = frame.getOperandStack().popInt();
        PrimitiveArray arr = (PrimitiveArray) frame.getOperandStack().popRef();
        arr.ints[idx] = num;
    }

    @Override
    public String toString() {
        return "bastore";
    }
}
