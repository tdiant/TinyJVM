package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class LUShrInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        int a = frame.getOperandStack().pop().getInt();
        long b = frame.getOperandStack().pop().getLong();
        int c = a & 0x3f; //5 low bit
        if (b >= 0) {
            frame.getOperandStack().push(new Slot(b >> c));
        } else {
            frame.getOperandStack().push(new Slot(
                    (2L << ~c) + (b >> c)
            ));
        }

    }

    @Override
    public String toString() {
        return "lushr";
    }
}
