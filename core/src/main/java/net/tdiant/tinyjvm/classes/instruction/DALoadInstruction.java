package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.PrimitiveArray;

public class DALoadInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        int idx = frame.getOperandStack().popInt();
        PrimitiveArray arr = (PrimitiveArray) frame.getOperandStack().popRef();
        frame.getOperandStack().pushDouble(arr.doubles[idx]);
    }

    @Override
    public String toString() {
        return "daload";
    }
}
