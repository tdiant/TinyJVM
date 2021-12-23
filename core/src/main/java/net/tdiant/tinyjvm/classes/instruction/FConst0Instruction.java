package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class FConst0Instruction extends Instruction {
    @Override
    public void run(Frame frame) {
        frame.getOperandStack().pushFloat(0.0F);
    }

    @Override
    public String toString() {
        return "fconst_0";
    }
}
