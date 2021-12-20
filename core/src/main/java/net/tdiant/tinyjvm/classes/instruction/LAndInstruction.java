package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class LAndInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        long a = frame.getOperandStack().pop().getLong();
        long b = frame.getOperandStack().pop().getLong();
        frame.getOperandStack().push(new Slot(
                a & b
        ));
    }

    @Override
    public String toString() {
        return "land";
    }
}
