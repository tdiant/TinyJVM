package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class IALoadInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        int idx = frame.getOperandStack().pop().getInt();
        //todo array support
    }

    @Override
    public String toString() {
        return "iaload";
    }
}
