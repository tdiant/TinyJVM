package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class DupX1Instruction extends Instruction {
    @Override
    public void run(Frame frame) {
        Slot s1 = frame.getOperandStack().pop();
        Slot s2 = frame.getOperandStack().pop();
        frame.getOperandStack().push(s1);
        frame.getOperandStack().push(s2);
        frame.getOperandStack().push(s1);
    }

    @Override
    public String toString() {
        return "dup_x1";
    }
}
