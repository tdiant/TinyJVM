package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class IUShrInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        int a = frame.getOperandStack().popInt();
        int b = frame.getOperandStack().popInt();
        int c = a & 0x1f; //5 low bit
        if (b >= 0) {
            frame.getOperandStack().pushInt(b >> c);
        } else {
            frame.getOperandStack().pushInt(
                    (2 << ~c) + (b >> c)
            );
        }

    }

    @Override
    public String toString() {
        return "iushr";
    }
}
