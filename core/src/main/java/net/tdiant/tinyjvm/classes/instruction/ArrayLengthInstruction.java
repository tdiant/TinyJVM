package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.BaseArrayInstance;
import net.tdiant.tinyjvm.runtime.Frame;

import java.util.Arrays;
import java.util.HashMap;

public class ArrayLengthInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        BaseArrayInstance arr = (BaseArrayInstance) frame.getOperandStack().popRef();
        frame.getOperandStack().pushInt(arr.getLength());

    }

    @Override
    public String toString() {
        return "arraylength";
    }
}
