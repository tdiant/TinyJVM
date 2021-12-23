package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.runtime.Field;
import net.tdiant.tinyjvm.runtime.Frame;
import net.tdiant.tinyjvm.runtime.Instance;
import net.tdiant.tinyjvm.runtime.UnionSlot;
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
            Instance obj = frame.getOperandStack().popRef();
            Field field = obj.getField(fieldName, fieldDescriptor);
            field.setVal(UnionSlot.of(RuntimeUtils.str2Obj("UTF-8", obj.getClazz().getClazzLoader())));
            field.pushStack(frame);
            return;
        }

        Instance obj = frame.getOperandStack().popRef();
        Field field = obj.getField(fieldName, fieldDescriptor);
        field.pushStack(frame);
    }

    @Override
    public String toString() {
        return "getfield " + clazz + " " + fieldName + " " + fieldDescriptor;
    }

}
