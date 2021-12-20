package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class Dup2Instruction extends Instruction {
    @Override
    public void run(Frame frame) {
        Slot v2 = frame.getOperandStack().pop();
        Slot v1 = frame.getOperandStack().pop();
        frame.getOperandStack().push(v1);
        frame.getOperandStack().push(v2);
        frame.getOperandStack().push(v1);
        frame.getOperandStack().push(v2);
    }

    @Override
    public String toString() {
        return "dup2";
    }
}
