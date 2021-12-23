package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class IXOrInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        int a = frame.getOperandStack().popInt();
        int b = frame.getOperandStack().popInt();
        frame.getOperandStack().pushInt(a ^ b);
    }

    @Override
    public String toString() {
        return "ixor";
    }
}
