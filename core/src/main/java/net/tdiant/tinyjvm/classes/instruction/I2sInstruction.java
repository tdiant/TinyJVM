package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class I2sInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        int x = frame.getOperandStack().popInt();
        frame.getOperandStack().push(new Slot((short) x));
    }

    @Override
    public String toString() {
        return "i2s";
    }
}
