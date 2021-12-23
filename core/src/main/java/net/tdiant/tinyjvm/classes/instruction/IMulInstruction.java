package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class IMulInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        int a = frame.getOperandStack().popInt();
        int b = frame.getOperandStack().popInt();
        frame.getOperandStack().push(new Slot(a * b));
    }

    @Override
    public String toString() {
        return "imul";
    }
}
