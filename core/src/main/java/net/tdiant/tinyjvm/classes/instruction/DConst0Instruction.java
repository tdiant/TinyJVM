package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class DConst0Instruction extends Instruction {
    @Override
    public void run(Frame frame) {
        frame.getOperandStack().pushDouble(0.0);
    }

    @Override
    public String toString() {
        return "dconst_0";
    }
}
