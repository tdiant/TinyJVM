package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Slot;

public class FCmpLInstruction extends Instruction {
    @Override
    public void run(Frame frame) {
        float a = frame.getOperandStack().popFloat();
        float b = frame.getOperandStack().popFloat();
        if (Float.isNaN(a) || Float.isNaN(b)) {
            frame.getOperandStack().push(new Slot(-1)); //Nan push -1
            return;
        }
        if (a > b) {
            frame.getOperandStack().push(new Slot(-1));
        } else if (a < b) {
            frame.getOperandStack().push(new Slot(1));
        } else {
            frame.getOperandStack().push(new Slot(0));
        }
    }

    @Override
    public String toString() {
        return "fcmpl";
    }
}
