package net.tdiant.tinyjvm.classes.instruction;

import net.tdiant.tinyjvm.TinyJVM;
import net.tdiant.tinyjvm.runtime.Clazz;
import net.tdiant.tinyjvm.runtime.Field;
import net.tdiant.tinyjvm.runtime.Frame;

public class PutStaticInstruction extends Instruction {

    public final String clazz;
    public final String fieldName;
    public final String fieldDescriptor;

    public PutStaticInstruction(String clazz, String fieldName, String fieldDescriptor) {
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
        Clazz cls = TinyJVM.vm.getHeap().getClazz(clazz);
        if (cls == null) {
            cls = frame.getMethod().getClazz().getClazzLoader().loadClazz(clazz);
        }
        Field field = cls.getField(fieldName, fieldDescriptor);
        field.set(frame);
    }

    @Override
    public String toString() {
        return "putstatic " + clazz + " " + fieldName + " " + fieldDescriptor;
    }


}
