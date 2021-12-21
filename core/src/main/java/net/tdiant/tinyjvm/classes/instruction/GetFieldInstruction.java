package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Field;
import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Instance;
import net.tdiant.tinyjvm.runtime.Slot;
import net.tdiant.tinyjvm.util.RuntimeUtils;

public class GetFieldInstruction extends Instruction {

    public final String clazz;
    public final String fieldName;
    public final String fieldDescriptor;

    public GetFieldInstruction(String clazz, String fieldName, String fieldDescriptor) {
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
        if (clazz.equals("java/nio/charset/Charset") && fieldName.equals("name")) {
            Instance obj = frame.getOperandStack().pop().getInstance();
            Field field = obj.getField(fieldName, fieldDescriptor);
            field.setVal(new Slot(RuntimeUtils.str2Obj("UTF-8", obj.getClazz().getClazzLoader())));
            field.get(frame);
            return;
        }

        Instance obj = frame.getOperandStack().pop().getInstance();
        Field field = obj.getField(fieldName, fieldDescriptor);
        field.get(frame);
    }

    @Override
    public String toString() {
        return "getfield " + clazz + " " + fieldName + " " + fieldDescriptor;
    }

}
