package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.*;

import java.util.Objects;

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
//        Slot s;
//        if (fieldDescriptor.equals("J") || fieldDescriptor.equals("D")) {
////            Slot low = frame.getOperandStack().pop();
//            Slot high = frame.getOperandStack().pop();
////            s = new Slot(high.getHigh());
//            s = high;
//        } else {
//            s = new Slot(frame.getOperandStack().pop().getHigh());
//        }
//
//        Instance self = frame.getOperandStack().popRef();
//        Field field = self.getField(fieldName, fieldDescriptor);
//        field.setVal(s);
        if (Objects.equals("java/lang/Thread", clazz)
                || Objects.equals("java/lang/ThreadGroup", clazz)) {
            return;
        }
        UnionSlot us = null;
        if (fieldDescriptor.equals("J") || fieldDescriptor.equals("D")) {
            final Slot low = frame.getOperandStack().pop();
            final Slot high = frame.getOperandStack().pop();
            us = UnionSlot.of(high, low);
        } else {
            us = UnionSlot.of(frame.getOperandStack().pop());
        }

        final Instance self = frame.getOperandStack().popRef();
        Field field = self.getField(fieldName, fieldDescriptor);
        field.setVal(us);
    }

    @Override
    public String toString() {
        return "putfield " + clazz + " " + fieldName + " " + fieldDescriptor;
    }
}
