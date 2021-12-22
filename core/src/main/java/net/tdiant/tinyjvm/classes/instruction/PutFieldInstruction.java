package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Field;
import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Instance;
import net.tdiant.tinyjvm.runtime.Slot;

public class PutFieldInstruction extends Instruction {

    public final String clazz;
    public final String fieldName;
    public final String fieldDescriptor;

    public PutFieldInstruction(String clazz, String fieldName, String fieldDescriptor) {
        this.clazz = clazz;
        this.fieldName = fieldName;
        this.fieldDescriptor = fieldDescriptor;
    }

    @Override
    public int delta() {
        return 3;
    }

    @Override
    public void run(Frame frame) {
        Slot s;
        if (fieldDescriptor.equals("J") || fieldDescriptor.equals("D")) {
            Slot low = frame.getOperandStack().pop();
            Slot high = frame.getOperandStack().pop();
            s = new Slot(high.getHigh(), low.getHigh());
        } else {
            s = new Slot(frame.getOperandStack().pop().getHigh());
        }

        Instance self = frame.getOperandStack().pop().getInstance();
        Field field = self.getField(fieldName, fieldDescriptor);
        field.setVal(s);
    }

    @Override
    public String toString() {
        return "putfield " + clazz + " " + fieldName + " " + fieldDescriptor;
    }
}
