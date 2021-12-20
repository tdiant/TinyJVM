package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class PopInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        frame.getOperandStack().pop();
    }

    @Override
    public String toString() {
        return "pop";
    }
}
