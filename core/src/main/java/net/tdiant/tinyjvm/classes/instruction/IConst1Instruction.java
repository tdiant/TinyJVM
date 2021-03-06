package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class IConst1Instruction extends Instruction {
    @Override
    public void run(Frame frame) {
        frame.getOperandStack().pushInt(1);
    }

    @Override
    public String toString() {
        return "iconst_1";
    }
}
