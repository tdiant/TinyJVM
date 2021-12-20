package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class IConst3Instruction extends Instruction {
    @Override
    public void run(Frame frame) {
        frame.getOperandStack().push(new Slot(3));
    }

    @Override
    public String toString() {
        return "iconst_3";
    }
}
