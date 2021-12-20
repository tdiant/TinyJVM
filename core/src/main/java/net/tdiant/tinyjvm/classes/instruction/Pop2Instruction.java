package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class Pop2Instruction extends Instruction {

    @Override
    public void run(Frame frame) {
        frame.getOperandStack().pop();
        frame.getOperandStack().pop();
    }

    @Override
    public String toString() {
        return "pop2";
    }
}
