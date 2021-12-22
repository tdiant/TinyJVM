package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.PrimitiveArray;

public class LAStoreInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        long num = frame.getOperandStack().pop().getLong();
        int idx = frame.getOperandStack().pop().getInt();
        PrimitiveArray arr = (PrimitiveArray) frame.getOperandStack().pop().getInstance();
        arr.longs[idx] = num;
    }

    @Override
    public String toString() {
        return "lastore";
    }
}
