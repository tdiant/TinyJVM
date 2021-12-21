package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.PrimitiveArray;
import net.tdiant.tinyjvm.runtime.Frame;

public class FAStoreInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        float num = frame.getOperandStack().pop().getFloat();
        int idx = frame.getOperandStack().pop().getInt();
        PrimitiveArray arr = (PrimitiveArray) frame.getOperandStack().pop().getInstance();
        arr.floats[idx] = num;
    }

    @Override
    public String toString() {
        return "fastore";
    }
}
