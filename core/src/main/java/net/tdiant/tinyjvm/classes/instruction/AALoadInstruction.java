package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.InstanceArrayInstance;
import net.tdiant.tinyjvm.runtime.Slot;

public class AALoadInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        int idx = frame.getOperandStack().pop().getInt();
        InstanceArrayInstance arr = (InstanceArrayInstance) frame.getOperandStack().pop().getInstance();
        frame.getOperandStack().push(new Slot(arr.get(idx)));
    }

    @Override
    public String toString() {
        return "aaload";
    }
}
