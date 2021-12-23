package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class ILoadNInstruction extends Instruction {

    private final int idx;

    public ILoadNInstruction(int idx) {
        this.idx = idx;
    }

    public int getIndex() {
        return idx;
    }

    @Override
    public void run(Frame frame) {
        frame.getOperandStack().pushInt(
                frame.getLocalVars().getInt(idx)
        );
    }

    @Override
    public String toString() {
        return "iload_" + idx;
    }


}