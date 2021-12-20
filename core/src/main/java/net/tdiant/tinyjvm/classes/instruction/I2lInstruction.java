package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class I2lInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        int x = frame.getOperandStack().pop().getInt();
        frame.getOperandStack().push(new Slot((long) x));
    }

    @Override
    public String toString() {
        return "i2l";
    }
}
