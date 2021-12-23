package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class LRemInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        long a = frame.getOperandStack().popLong();
        long b = frame.getOperandStack().popLong();
        frame.getOperandStack().pushLong(b % a);
    }

    @Override
    public String toString() {
        return "lrem";
    }
}
