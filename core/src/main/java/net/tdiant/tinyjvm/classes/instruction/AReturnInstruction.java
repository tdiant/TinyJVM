package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.util.RuntimeUtils;

public class AReturnInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        RuntimeUtils.doReturn(1);
    }

    @Override
    public String toString() {
        return "areturn";
    }
}
