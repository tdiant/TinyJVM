package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class NopInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
    }

    @Override
    public String toString() {
        return "nop";
    }
}
