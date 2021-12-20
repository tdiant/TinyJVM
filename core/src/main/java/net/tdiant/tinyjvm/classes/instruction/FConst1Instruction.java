package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class FConst1Instruction extends Instruction {
    @Override
    public void run(Frame frame) {
        frame.getOperandStack().push(new Slot(1.0F));
    }

    @Override
    public String toString() {
        return "fconst_1";
    }
}
