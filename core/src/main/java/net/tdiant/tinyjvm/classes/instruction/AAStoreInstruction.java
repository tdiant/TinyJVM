package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Instance;
import net.tdiant.tinyjvm.runtime.BaseArrayInstance;

public class AAStoreInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        Instance num = frame.getOperandStack().pop().getInstance();
        int idx = frame.getOperandStack().pop().getInt();
        BaseArrayInstance arr = (BaseArrayInstance) frame.getOperandStack().pop().getInstance();
        arr.items[idx] = num;
    }

    @Override
    public String toString() {
        return "aastore";
    }
}
