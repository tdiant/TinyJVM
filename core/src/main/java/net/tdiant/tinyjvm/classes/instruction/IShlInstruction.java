package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class IShlInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        int a = frame.getOperandStack().popInt();
        int b = frame.getOperandStack().popInt();
        //An int result is calculated by shifting value1 left by s bit positions,
        //where s is the value of the low 5 bits of value2.
        frame.getOperandStack().push(new Slot(
                b << (a & 0x1f)
        ));
    }

    @Override
    public String toString() {
        return "ishl";
    }
}
