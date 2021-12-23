package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class IDivInstruction extends Instruction {

    @Override
    public void run(Frame frame) {
        int a = frame.getOperandStack().popInt();
        int b = frame.getOperandStack().popInt();
        if (a == 0) throw new NumberFormatException();
        frame.getOperandStack().push(new Slot(b / a));
    }

    @Override
    public String toString() {
        return "idiv";
    }

}
