package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class AconstNullInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        frame.getOperandStack().pushRef(null);
    }

    @Override
    public String toString() {
        return "aconstnull";
    }
}
