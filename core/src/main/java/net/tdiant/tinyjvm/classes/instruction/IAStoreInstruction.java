package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.PrimitiveArray;

public class IAStoreInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        int num = frame.getOperandStack().pop().getInt();
        int idx = frame.getOperandStack().pop().getInt();
        PrimitiveArray arr = (PrimitiveArray) frame.getOperandStack().pop().getInstance();
        arr.ints[idx] = num;
    }

    @Override
    public String toString() {
        return "iastore";
    }
}
