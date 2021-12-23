package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.PrimitiveArray;

public class CALoadInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        int idx = frame.getOperandStack().popInt();
        PrimitiveArray arr = (PrimitiveArray) frame.getOperandStack().popRef();
        frame.getOperandStack().pushInt(arr.ints[idx]);
    }

    @Override
    public String toString() {
        return "caload";
    }
}
