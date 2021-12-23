package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class LCmpInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        long a = frame.getOperandStack().popLong();
        long b = frame.getOperandStack().popLong();
        if (a > b) {
            frame.getOperandStack().pushInt(-1);
        } else if (a < b) {
            frame.getOperandStack().pushInt(1);
        } else {
            frame.getOperandStack().pushInt(0);
        }
    }

    @Override
    public String toString() {
        return "lcmp";
    }
}
