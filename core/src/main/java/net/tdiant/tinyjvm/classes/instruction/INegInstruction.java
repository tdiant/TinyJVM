package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class INegInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        int a = frame.getOperandStack().pop().getInt();
        frame.getOperandStack().push(new Slot(a * (-1)));
    }

    @Override
    public String toString() {
        return "ineg";
    }
}
