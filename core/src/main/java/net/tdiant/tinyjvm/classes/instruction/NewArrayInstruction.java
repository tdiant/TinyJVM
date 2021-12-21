package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.PrimitiveArray;
import net.tdiant.tinyjvm.runtime.Slot;

public class NewArrayInstruction extends Instruction {

    public final int type;

    public NewArrayInstruction(int type) {
        this.type = type;
    }

    @Override
    public int delta() {
        return 2;
    }

    @Override
    public void run(Frame frame) {
        int count = frame.getOperandStack().pop().getInt();

        switch (type) {
            case 4:
                frame.getOperandStack().push(new Slot(PrimitiveArray.boolArray(count)));
                return;
            case 5:
                frame.getOperandStack().push(new Slot(PrimitiveArray.charArray(count)));
                return;
            case 6:
                frame.getOperandStack().push(new Slot(PrimitiveArray.floatArray(count)));
                return;
            case 7:
                frame.getOperandStack().push(new Slot(PrimitiveArray.doubleArray(count)));
                return;
            case 8:
                frame.getOperandStack().push(new Slot(PrimitiveArray.byteArray(count)));
                return;
            case 9:
                frame.getOperandStack().push(new Slot(PrimitiveArray.shortArray(count)));
                return;
            case 10:
                frame.getOperandStack().push(new Slot(PrimitiveArray.intArray(count)));
                return;
            case 11:
                frame.getOperandStack().push(new Slot(PrimitiveArray.longArray(count)));
                return;
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public String toString() {
        return "newarray " + type;
    }
}
