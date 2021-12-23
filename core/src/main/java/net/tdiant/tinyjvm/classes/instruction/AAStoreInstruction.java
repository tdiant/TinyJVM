package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.InstanceArrayInstance;

public class AAStoreInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        Object val = frame.getOperandStack().popRef();
        int index = frame.getOperandStack().popInt();
        InstanceArrayInstance array = (InstanceArrayInstance) frame.getOperandStack().popRef();
        array.set(index, val);
    }

    @Override
    public String toString() {
        return "aastore";
    }
}
