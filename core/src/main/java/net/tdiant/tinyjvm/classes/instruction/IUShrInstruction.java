package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class IUShrInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        int a = frame.getOperandStack().pop().getInt();
        int b = frame.getOperandStack().pop().getInt();
        int c = a & 0x1f; //5 low bit
        if (b >= 0) {
            frame.getOperandStack().push(new Slot(b >> c));
        } else {
            frame.getOperandStack().push(new Slot(
                    (2 << ~c) + (b >> c)
            ));
        }

    }

    @Override
    public String toString() {
        return "iushr";
    }
}
