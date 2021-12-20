package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class Lconst0Instruction extends Instruction {
    @Override
    public void run(Frame frame) {
        frame.getOperandStack().push(new Slot(0L));
    }

    @Override
    public String toString() {
        return "lconst_0";
    }
}
