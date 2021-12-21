package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class WideInstruction extends Instruction {

    public final int offset;
    public final Instruction inst;

    public WideInstruction(int offset, Instruction inst) {
        this.offset = offset;
        this.inst = inst;
    }

    @Override
    public int delta() {
        return offset;
    }

    @Override
    public void run(Frame frame) {
        inst.run(frame);
    }

    @Override
    public String toString() {
        return "wide " + offset + " " + inst.toString();
    }
}
