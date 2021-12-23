package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.PrimitiveArray;

public class FAStoreInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        float num = frame.getOperandStack().popFloat();
        int idx = frame.getOperandStack().popInt();
        PrimitiveArray arr = (PrimitiveArray) frame.getOperandStack().popRef();
        arr.floats[idx] = num;
    }

    @Override
    public String toString() {
        return "fastore";
    }
}
