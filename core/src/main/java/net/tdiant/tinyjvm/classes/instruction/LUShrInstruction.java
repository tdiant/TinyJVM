package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;

public class LUShrInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        int a = frame.getOperandStack().popInt();
        long b = frame.getOperandStack().popLong();
        int c = a & 0x3f; //5 low bit
        if (b >= 0) {
            frame.getOperandStack().pushLong(b >> c);
        } else {
            frame.getOperandStack().pushLong(
                    (2L << ~c) + (b >> c)
            );
        }

    }

    @Override
    public String toString() {
        return "lushr";
    }
}
