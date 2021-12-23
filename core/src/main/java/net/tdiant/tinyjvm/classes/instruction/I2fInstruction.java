package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class I2fInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        int x = frame.getOperandStack().popInt();
        frame.getOperandStack().pushFloat(x);
    }

    @Override
    public String toString() {
        return "i2f";
    }
}
