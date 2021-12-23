package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class Lconst0Instruction extends Instruction {
    @Override
    public void run(Frame frame) {
        frame.getOperandStack().pushLong(0L);
    }

    @Override
    public String toString() {
        return "lconst_0";
    }
}
