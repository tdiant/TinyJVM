package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class LdcInstruction extends Instruction {

    private final String descriptor;
    private final Object obj;

    public LdcInstruction(String descriptor, Object obj) {
        this.descriptor = descriptor;
        this.obj = obj;
    }

    @Override
    public int delta() {
        return 2;
    }

    @Override
    public void run(Frame frame) {
//todo
    }

    @Override
    public String toString() {
        return "ldc";
    }
}
