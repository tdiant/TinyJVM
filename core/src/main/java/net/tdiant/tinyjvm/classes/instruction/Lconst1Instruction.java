package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class Lconst1Instruction extends Instruction {
    @Override
    public void run(Frame frame) {
        frame.getOperandStack().pushLong(1L);
    }

    @Override
    public String toString() {
        return "lconst_1";
    }
}
