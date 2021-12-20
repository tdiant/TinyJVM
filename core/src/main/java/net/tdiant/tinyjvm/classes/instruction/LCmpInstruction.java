package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class LCmpInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        long a = frame.getOperandStack().pop().getLong();
        long b = frame.getOperandStack().pop().getLong();
        if (a > b) {
            frame.getOperandStack().push(new Slot(-1));
        } else if (a < b) {
            frame.getOperandStack().push(new Slot(1));
        } else {
            frame.getOperandStack().push(new Slot(0));
        }
    }

    @Override
    public String toString() {
        return "lcmp";
    }
}
