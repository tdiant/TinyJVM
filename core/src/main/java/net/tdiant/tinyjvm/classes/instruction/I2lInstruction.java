package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class I2lInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        int x = frame.getOperandStack().popInt();
        frame.getOperandStack().pushLong(x);
    }

    @Override
    public String toString() {
        return "i2l";
    }
}
