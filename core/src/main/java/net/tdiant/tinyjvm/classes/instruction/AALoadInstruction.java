package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Instance;
import net.tdiant.tinyjvm.runtime.InstanceArrayInstance;

import java.util.Arrays;

public class AALoadInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        System.out.println(Arrays.toString(frame.getOperandStack().getSlots()));
        int idx = frame.getOperandStack().popInt();
        InstanceArrayInstance arr = (InstanceArrayInstance) frame.getOperandStack().popRef();
        frame.getOperandStack().pushRef((Instance) arr.get(idx));
    }

    @Override
    public String toString() {
        return "aaload";
    }
}
