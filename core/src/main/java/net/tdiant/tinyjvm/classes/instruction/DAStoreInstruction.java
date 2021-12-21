package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.PrimitiveArray;
import net.tdiant.tinyjvm.runtime.Frame;

public class DAStoreInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        double num = frame.getOperandStack().pop().getDouble();
        int idx = frame.getOperandStack().pop().getInt();
        PrimitiveArray arr = (PrimitiveArray) frame.getOperandStack().pop().getInstance();
        arr.doubles[idx] = num;
    }

    @Override
    public String toString() {
        return "dastore";
    }
}
