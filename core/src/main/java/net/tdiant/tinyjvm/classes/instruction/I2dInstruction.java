package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class I2dInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        long x = frame.getOperandStack().popLong();
        frame.getOperandStack().pushDouble((double) x);
    }

    @Override
    public String toString() {
        return "l2d";
    }
}
