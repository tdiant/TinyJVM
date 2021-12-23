package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.PrimitiveArray;

public class DAStoreInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        double num = frame.getOperandStack().popDouble();
        int idx = frame.getOperandStack().popInt();
        PrimitiveArray arr = (PrimitiveArray) frame.getOperandStack().popRef();
        arr.doubles[idx] = num;
    }

    @Override
    public String toString() {
        return "dastore";
    }
}
