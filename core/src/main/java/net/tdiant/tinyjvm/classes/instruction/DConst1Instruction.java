package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class DConst1Instruction extends Instruction {
    @Override
    public void run(Frame frame) {
        frame.getOperandStack().push(new Slot(1.0));
    }

    @Override
    public String toString() {
        return "dconst_1";
    }
}
