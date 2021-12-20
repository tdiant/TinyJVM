package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class DupInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        Slot s = frame.getOperandStack().pop();
        frame.getOperandStack().push(s);
        frame.getOperandStack().push(s);
    }

    @Override
    public String toString() {
        return "dup";
    }
}
